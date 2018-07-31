package com.rbac.application.action.core;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther ttm
 * @date 2018/7/31
 */
public class RbacAction extends ActionSupport {

    /**
     * 登录用户
     */
    private String loginName;

    public void _execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("User".equals(name)) {
                setLoginName(cookie.getValue());
                break;
            }
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
