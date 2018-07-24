package com.system.core.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/23
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private List<String> allowRequestUrl;

    private final static String ERROR_PAGE = "error";

    public AuthenticationInterceptor() {
        initAllowRequestUrl();
    }

    /**
     * 初始化允许请求的URL
     */
    private void initAllowRequestUrl() {
        allowRequestUrl = new ArrayList<>();
        allowRequestUrl.add("/admin/vlogin");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        LOG.info("Show requestUri: {}, contextPath: {}, method: {}", requestUri, contextPath, method);
        checkoutLoginStatus(request);
        //判断对应的请求url 时候符合 并且 用户时候是登录状态
        if (!allowRequestUrl.contains(requestUri)) {
            return ERROR_PAGE;
        }

        return actionInvocation.invoke();
    }


    /**
     * 判断登录状态
     * @param request
     * @return
     */
    private boolean checkoutLoginStatus(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String secretKey = "";
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("SecretKey")) {
                secretKey = cookie.getValue();
                break;
            }
        }

        LOG.info("Checkout Login status: " + secretKey);
        return true;
    }



}
