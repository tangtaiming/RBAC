package com.rbac.application.service;

import com.rbac.application.action.vo.*;
import com.rbac.application.dao.UserDao;
import com.rbac.application.dao.UserRoleDao;
import com.rbac.application.orm.User;
import com.rbac.application.orm.UserRole;
import com.system.util.base.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class UserService extends SimpleCoreService<User> {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao = new UserDao(User.class);

    private UserRoleDao userRoleDao = new UserRoleDao(UserRole.class);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(String id) {
        Integer userId = Integer.valueOf(id);
        User user = findUserOne(Integer.valueOf(id));
        if (!(null == user)) {
            boolean deleteFalg = userDao.delete(user);
            LOG.info("Delete userId: " + userId + " user name: " + user.getName() + " result: " + getResult(deleteFalg));
            deleteUserRoleByUserId(userId, deleteFalg);
        }
    }

    /**
     * 删除用户角色
     * @param userId
     * @param userDeleteFalg
     */
    private void deleteUserRoleByUserId(Integer userId, boolean userDeleteFalg) {
        if (userDeleteFalg) {
            List<UserRole> userRoleList = findUserRoleByUserId(userId);
            if (!CollectionUtils.isEmpty(userRoleList)) {
                for (UserRole userRole : userRoleList) {
                    boolean deleteuserRoleFalg = userRoleDao.delete(userRole);
                    LOG.info("Delete user role id: " + userRole.getId() + " result: " + getResult(deleteuserRoleFalg));
                }
            }
        }
    }

    public UserVo findUserVoById(String id) {
        User user = findUserOne(Integer.valueOf(id));
        if (null == user) {
            throw new IllegalArgumentException("user entity is empty");
        }

        List<Integer> chosenRoleList = findUserChosenRole(user.getId());
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setEmail(user.getEmail());
        userVo.setName(user.getName());
        userVo.setRoles(chosenRoleList);
        return userVo;
    }

    public User findUserOne(Serializable uid) {
        return userDao.findOne(Integer.valueOf(uid.toString()));
    }

    public int findUserAllListCount() {
        return userDao.findAllListCount();
    }

    public String findUserByLoginVo(LoginVo userVo) {
        String loginName = userVo.getLoginName();
        String voPassowd = userVo.getPassword();
        User findUser = userDao.findUserByName(loginName);
        if (null == findUser) {
            //do to
            return JsonUtils.toJson(ResultUtils.fail("输入账号不正确"));
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        String userAgent = request.getHeader("user-agent");
        Integer userId = findUser.getId();
        String name = findUser.getName();
        String findPassword = findUser.getPassword();
        String md5Password = MD5Utils.encoder(voPassowd);
        if (!findPassword.equals(md5Password)) {
            //go to
            return JsonUtils.toJson(ResultUtils.fail("输入密码不正确"));
        }

        String userInfoToString = userId + name + findPassword + userAgent;
        String autoToken = MD5Utils.encoder(userInfoToString);
        String secretKey = autoToken + "#" + userId;
        LOG.info("Secret key: " + secretKey);

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("secretKey", secretKey);
        return JsonUtils.toJson(ResultUtils.success());
    }

    public User findUserByName(String name) {
        User findUser = userDao.findUserByName(name);
        return findUser;
    }

    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> findUserList() {
        return userDao.findAllList();
    }

    /**
     * 获取所有用户信息
     * @return
     */
    public List<UserManagementRsVo> findUserManagementRsVo() {
        List<User> userList = userDao.findAllList();
        List<UserManagementRsVo> userManagementRsVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(userList)) {
            for (User user : userList) {
                UserManagementRsVo userRsDto = new UserManagementRsVo(user);
                userManagementRsVoList.add(userRsDto);
            }
        }

        return userManagementRsVoList;
    }

    public boolean saveUser(SaveUserReVo user) {
        Integer userId = user.getId();
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        //-1 代表不是超级管理员
        Integer admin = -1;
        //1 代表对于账号开启使用状态， 0 表示账号冻结
        Integer status = 1;
        //1 代表账号正常, 0 代表账号锁定
        Integer locked = 1;
        List<Integer> roles = Optional.ofNullable(user.getRoles()).orElse(new ArrayList<>());
        boolean saveFalg = false;
        if (null != userId) {
            User findUser = findUserOne(userId);
            if (null != findUser) {
                findUser.setUpdateDate(currentTime);
                findUser.setName(user.getName());
                findUser.setEmail(user.getEmail());
                userDao.update(findUser);
                saveFalg = true;
            }
        } else {
            User createUser = new User();
            String email = user.getEmail();
            String name = user.getName();
            String password = user.getPassword();
            createUser.setLocked(locked);
            createUser.setEmail(email);
            createUser.setName(name);
            createUser.setAdmin(admin);
            createUser.setStatus(status);
            createUser.setCreateDate(currentTime);
            createUser.setUpdateDate(currentTime);
            createUser.setPassword(password);
            PasswordUtls passwordUtls = new PasswordUtls();
            passwordUtls.encryptPassword(createUser);
            Integer guid = (Integer) userDao.save(createUser);
            if (null != guid) {
                saveFalg = true;
                userId = guid;
            }
        }

        //保存数据成功之后 获取用户角色进行数据存储
        if (saveFalg) {
            /**
             * 找出删除的角色
             * 假如已有的角色集合是A，界面传递过得角色集合是B
             * 角色集合A当中的某个角色不在角色集合B当中，就应该删除
             */
             List<UserRole> userRoleList = findUserRoleByUserId(userId);
             List<Integer> relatedRoleIds = new ArrayList<>();
             if (CollectionUtils.isNotEmpty(userRoleList)) {
                 Iterator<UserRole> it = userRoleList.iterator();
                 for ( ;it.hasNext(); ) {
                     UserRole userRole = it.next();
                     Integer tmpRoleId = userRole.getRoleId();
                     Integer tmpUserRoleId = userRole.getId();
                     relatedRoleIds.add(tmpRoleId);
                     if (!roles.contains(tmpRoleId)) {
                         boolean deleteFalg = userRoleDao.delete(userRole);
                         LOG.info("Delete user role relation id: " + tmpUserRoleId + (deleteFalg ? " success" : " fail"));
                     }
                 }
             }
            /**
             * 找出添加的角色
             * 假如已有的角色集合是A，界面传递过得角色集合是B
             * 角色集合B当中的某个角色不在角色集合A当中，就应该添加
             */
            if (CollectionUtils.isNotEmpty(roles)) {
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
                for (Integer role : roles) {
                    if (!relatedRoleIds.contains(role)) {
                        UserRole createUserRole = new UserRole();
                        createUserRole.setCreateDate(time);
                        createUserRole.setRoleId(role);
                        createUserRole.setUserId(userId);
                        Integer createUserRoleId = (Integer) userRoleDao.save(createUserRole);
                        LOG.info("Save user role relation id: " + createUserRoleId + ((null != createUserRoleId) ? " success" : " fail"));
                    }
                }
             }

        }
        return saveFalg;
    }

    public List<UserRole> findUserRoleByUserId(Integer userId) {
        return userRoleDao.findUserRoleByUserId(userId);
    }

    /**
     * 查询用户选中角色
     * @param userId
     * @return
     */
    public List<Integer> findUserChosenRole(Integer userId) {
        List<Integer> chosenVoList = new ArrayList<>();
        List<UserRole> findUserRoleList = findUserRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(findUserRoleList)) {
            for (UserRole userRole : findUserRoleList) {
                chosenVoList.add(userRole.getRoleId());
            }
        }

        return chosenVoList;
    }

    /**
     * 根据用户id查下用户对于角色
     * @param userId
     * @return
     */
    public List<Integer> findUserRoleColumnRoleIdByUserId(Integer userId) {
        return userRoleDao.findUserRoleColumnRoleIdByUserId(userId);
    }

    public List<User> findUserListTest() {
        //获取当前页 和每页显示数量 与分页情况
//        FilterSession filterSession = new FilterSession();
//        Specification specification = filterSession.getSpecification();
//        PageSession pageSession = new PageSession();
//        Pageable pageable = pageSession.getPageable();

//        SimpleSpecificationBuilder builder = filterSession.initSpecificationBuilder();
//        Specification specification = builder.generateSpecification();

//        SimpleOrderableBuilder orderableBuilder = new SimpleOrderableBuilder();
//
//        SimplePageableBuilder pageableBuilder = new SimplePageableBuilder();
//        Pageable pageable = pageableBuilder.addPageable(1, 20);
        return userDao.findDataList();
    }

    /**
     * 登录名查询数据
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findUserByName(username);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public boolean changePassword(User user) {
        PasswordUtls passwordUtls = new PasswordUtls();
        passwordUtls.encryptPassword(user);
        userDao.update(user);
        return true;
    }

    @Override
    public List<User> getDataList() {
        return userDao.findDataList();
    }

    @Override
    public PageUtils getPage() {
        return userDao.findPage();
    }

}
