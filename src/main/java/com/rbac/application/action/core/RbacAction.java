package com.rbac.application.action.core;

import com.opensymphony.xwork2.ActionSupport;
import com.system.core.vo.NavigatorRsVo;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther ttm
 * @date 2018/7/31
 */
public class RbacAction extends ActionSupport {

    /**
     * 导航菜单
     */
    private NavigatorRsVo nav;

    /**
     * 登录用户
     */
    private String loginName;

    private static String propertyName = "package";

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
        //实例化导航
        nav = new NavigatorRsVo();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public NavigatorRsVo getNav() {
        return nav;
    }

    public void setNav(NavigatorRsVo nav) {
        this.nav = nav;
    }

}
