package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.vo.CreateUserRsVo;
import com.rbac.application.action.vo.EditUserRsVo;
import com.rbac.application.action.vo.SaveUserReVo;
import com.rbac.application.action.vo.UserManagementRsVo;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.User;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import com.system.util.base.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class UserManagementAction extends RbacAction {

    private final static Logger LOG = LoggerFactory.getLogger(UserManagementAction.class);

    private UserService userService = new UserService();

    private RoleService roleService = new RoleService();

    private static final String ERROR_KEY = "error";

    private Integer id;

    private String result;

    private List<UserManagementRsVo> userRsVo;

    private CreateUserRsVo createUserRsVo;

    private EditUserRsVo editUserRsVo;

    private SaveUserReVo userReVo;

    /**
     * 用户列表
     */
    private String name;

    public String userManagement() {
        _execute();
        userRsVo = userService.findUserManagementRsVo();
        return SUCCESS;
    }

    /**
     * 伪登录
     * @return
     */
    public String vlogin() {
        //传入登录名是否为空
        if (StringUtils.isEmpty(name)) {
            addFieldError(ERROR_KEY, "登录失败,登录名称不能为空!");
            return INPUT;
        }

        //查下用户是否存在
        User user = userService.findUserByName(name);
        if (null == user) {
            addFieldError(ERROR_KEY, "登录失败,用户不存在,请重新输入登录名称!");
            return INPUT;
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        String userAgent = request.getHeader("user-agent");
        Integer userId = user.getId();
        String userInfoToString = userId + user.getName() + user.getEmail() + userAgent;
        String autoToken = MD5Utils.encoder(userInfoToString);
        String secretKey = autoToken + "#" + userId;
        LOG.info("Secret key: " + secretKey);
        //创建Cookie
        Cookie cookie = new Cookie("User", user.getName());
        cookie.setPath("/");
        Cookie cookieToken = new Cookie("SecretKey", secretKey);
        cookieToken.setPath("/");

        ServletActionContext.getResponse().addCookie(cookie);
        ServletActionContext.getResponse().addCookie(cookieToken);
        return SUCCESS;
    }

    /**
     * 退出登录
     * @return
     */
    public String signOut() {
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if ("User".equals(cookieName) || "SecretKey".equals(cookieName)) {
                cookie.setValue(null);
                cookie.setPath("/");
                ServletActionContext.getResponse().addCookie(cookie);
            }
        }
        return SUCCESS;
    }

    public String createUser() {
        _execute();
        List<Role> roleList = roleService.findRoleList();
        createUserRsVo = new CreateUserRsVo(roleList);
        return SUCCESS;
    }

    public void validateSaveUserSaveUser() {
        if (StringUtils.isEmpty(userReVo.getName())) {
            addFieldError(ERROR_KEY, "保存用户名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(userReVo.getEmail())) {
            addFieldError(ERROR_KEY, "保存用户邮箱不能为空");
            return;
        }

        if (null == userReVo.getId()) {
            User findUser = userService.findUserByName(userReVo.getName());
            if (null != findUser) {
                addFieldError(ERROR_KEY, "保存用户名称已经存在，请进行数据修改!");
                return;
            }
        }
    }

    public String saveUser() {
        userService.saveUser(userReVo);
        setResult("success");
        return SUCCESS;
    }

    public String editUser() {
        _execute();
        User user = userService.findUserOne(getId());
        if (null == user) {
            addFieldError(ERROR_KEY, "编辑异常, 查询用户为空");
            return INPUT;
        }

        List<Role> roleList = roleService.findRoleList();
        List<Integer> chosenRoleList = userService.findUserChosenRole(user.getId());
        editUserRsVo = new EditUserRsVo(user, roleList, chosenRoleList);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<UserManagementRsVo> getUserRsVo() {
        return userRsVo;
    }

    public void setUserRsVo(List<UserManagementRsVo> userRsVo) {
        this.userRsVo = userRsVo;
    }

    public CreateUserRsVo getCreateUserRsVo() {
        return createUserRsVo;
    }

    public void setCreateUserRsVo(CreateUserRsVo createUserRsVo) {
        this.createUserRsVo = createUserRsVo;
    }

    public EditUserRsVo getEditUserRsVo() {
        return editUserRsVo;
    }

    public void setEditUserRsVo(EditUserRsVo editUserRsVo) {
        this.editUserRsVo = editUserRsVo;
    }

    public SaveUserReVo getUserReVo() {
        return userReVo;
    }

    public void setUserReVo(SaveUserReVo userReVo) {
        this.userReVo = userReVo;
    }
}
