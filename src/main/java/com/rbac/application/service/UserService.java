package com.rbac.application.service;

import com.rbac.application.action.dto.UserDto;
import com.rbac.application.action.dto.UserRsDto;
import com.rbac.application.dao.UserDao;
import com.rbac.application.dao.UserRoleDao;
import com.rbac.application.orm.User;
import com.rbac.application.orm.UserRole;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class UserService {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    private List<User> userList;

    private UserDao userDao = new UserDao(User.class);

    private UserRoleDao userRoleDao = new UserRoleDao(UserRole.class);

    public User findUserOne(Integer uid) {
        return userDao.findOne(uid);
    }

    public UserDto findStaticUserDtoOne(Integer uid) {
        User userOne = findUserOne(uid);
        if (null != userOne) {
            Integer id = userOne.getId();
            String email = userOne.getEmail();
            String name = userOne.getName();
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setEmail(email);
            userDto.setName(name);
            return userDto;
        }

        return null;
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

    public boolean saveUser(UserRsDto user) {
        Integer userId = user.getId();
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        //-1 代表不是超级管理员
        Integer admin = -1;
        //1 代表对于账号开启使用状态， 0 表示账号冻结
        Integer status = 1;
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
            createUser.setEmail(email);
            createUser.setName(name);
            createUser.setAdmin(admin);
            createUser.setStatus(status);
            createUser.setCreateDate(currentTime);
            createUser.setUpdateDate(currentTime);
            Integer guid = userDao.save(createUser);
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
                        Integer createUserRoleId = userRoleDao.save(createUserRole);
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
        List<Integer> chosenDtoList = new ArrayList<>();
        List<UserRole> findUserRoleList = findUserRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(findUserRoleList)) {
            for (UserRole userRole : findUserRoleList) {
                chosenDtoList.add(userRole.getRoleId());
            }
        }

        return chosenDtoList;
    }

}
