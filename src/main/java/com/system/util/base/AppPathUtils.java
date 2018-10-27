package com.system.util.base;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.File;

public class AppPathUtils {

    public static final String SEPARATORCHAR = File.separatorChar + "";

    public static String getAppPath() {
        ActionContext actionContext = ActionContext.getContext();
        ServletContext sc = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
        String appPath = sc.getRealPath("/");
        System.out.println("app path: " + appPath);
        return appPath;
    }

    /**
     * 获取管理页面配置xml路径
     * @return
     */
    public static String getAppPageXmlPath() {
        return getAppPath() + "WEB-INF" +
                SEPARATORCHAR + "classes" +
                SEPARATORCHAR + "com" +
                SEPARATORCHAR + "page" +
                SEPARATORCHAR;
    }

}
