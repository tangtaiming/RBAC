package com.rbac.application.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.User;
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
public class UserManagementAction extends ActionSupport {

    private final static Logger LOG = LoggerFactory.getLogger(UserManagementAction.class);

    private UserService userService = new UserService();

    private static final String ERROR_KEY = "error";

    private List<User> userList;

    private User user;

    private Integer id;

    private String result;

    /**
     * 用户name
     */
    private String name;

    public String userManagement() {
        LOG.info("User management {}...", System.currentTimeMillis());
        List<User> userList = userService.findUserList();
        setUserList(userList);
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
        return SUCCESS;
    }

    public void validateSaveUser() {
        if (StringUtils.isEmpty(user.getName())) {
            addFieldError(ERROR_KEY, "保存用户名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            addFieldError(ERROR_KEY, "保存用户邮箱不能为空");
            return;
        }

        User findUser = userService.findStaticUserByName(user.getName());
        if (null != findUser) {
            addFieldError(ERROR_KEY, "保存用户名称已经存在，请进行数据修改!");
            return;
        }
    }

    public String saveUser() {
        userService.saveUser(user);
        setResult("success");
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
