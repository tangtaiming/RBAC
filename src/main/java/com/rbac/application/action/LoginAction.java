package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.vo.LoginVo;
import com.rbac.application.action.vo.ResponseVo;
import com.rbac.application.orm.User;
import com.rbac.application.service.UserService;
import com.system.core.session.RbacSession;
import com.system.util.base.AutoTokenUtils;
import com.system.util.base.JsonUtils;
import com.system.util.base.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录Action
 * @auther ttm
 * @date 2018/8/18 0018
 **/
public class LoginAction extends RbacAction {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAction.class);

    private static final String ERROR_KEY = "error";

    /**
     * 用户列表
     */
    private String name;

    private UserService userService = new UserService();

    private LoginVo userVo;

    private String responseJson;

    /**
     * 用户登录
     * @return
     */
    public String login() {
        return SUCCESS;
    }

    /**
     * 处理登录
     * @return
     */
    public String doLogin() {
        LOG.info("Show vo: " + userVo.toString());
        String responseVo = userService.findUserByLoginVo(userVo);
        setResponseJson(responseVo);
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
        RbacSession session = new RbacSession();


        Integer userId = user.getId();
        String autoToken = AutoTokenUtils.createAutoToken(user, request);
        String secretKey = autoToken + "#" + userId;
        LOG.info("Secret key: " + secretKey);
        session.put("user", user.getName());
        session.put("secretKey", secretKey);
        //创建Cookie
//        Cookie cookie = new Cookie("User", user.getName());
//        cookie.setPath("/");
//        Cookie cookieToken = new Cookie("SecretKey", secretKey);
//        cookieToken.setPath("/");
//
//        ServletActionContext.getResponse().addCookie(cookie);
//        ServletActionContext.getResponse().addCookie(cookieToken);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginVo getUserVo() {
        return userVo;
    }

    public void setUserVo(LoginVo userVo) {
        this.userVo = userVo;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}
