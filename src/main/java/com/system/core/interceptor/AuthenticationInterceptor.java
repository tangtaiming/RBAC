package com.system.core.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rbac.application.orm.Access;
import com.rbac.application.orm.User;
import com.rbac.application.service.AccessService;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import com.system.util.base.JsonUtils;
import com.system.util.base.MD5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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

    private List<String> ignoreRequestUrl;

    private final static String ERROR_PAGE = "error";

    private UserService userService = new UserService();

    private RoleService roleService = new RoleService();

    private AccessService accessService = new AccessService();

    private User user;

    public AuthenticationInterceptor() {
        initAllowRequestUrl();
    }

    public List<String> privilegeUrls;

    /**
     * 初始化允许请求的URL
     */
    private void initAllowRequestUrl() {
        allowRequestUrl = new ArrayList<>();
        allowRequestUrl.add("/admin/vlogin");
        allowRequestUrl.add("/admin/signOut");

        ignoreRequestUrl = new ArrayList<>();
        ignoreRequestUrl.add("/admin/vlogin");
        ignoreRequestUrl.add("/admin/signOut");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        LOG.info("Show requestUri: {}, contextPath: {}, method: {}", requestUri, contextPath, method);
        //判断对应的请求url 时候符合 并且 用户时候是登录状态
        if (!allowRequestUrl.contains(requestUri) && !checkoutLoginStatus(request)) {
            return ERROR_PAGE;
        }

        //判断是否拥有对应链接权限
        if (!checkPrivilege(requestUri)) {
            return ERROR_PAGE;
        }

        return actionInvocation.invoke();
    }

    private boolean checkPrivilege(String url) {
        //如果是超级管理员
        //超级管理员
        Integer isAdmin = 1;
        if (null != user && user.getAdmin().equals(isAdmin)) {
            return true;
        }

        //忽略请求
        if (inArray(url, ignoreRequestUrl)) {
            return true;
        }

        return inArray(url, findRolePrivilege(null));
    }

    private boolean inArray(String verificationData, List<String> array) {
        return array.contains(verificationData);
    }

    private List<String> findRolePrivilege(Integer userId) {
        if ((null == userId) && (null != user)) {
            userId = user.getId();
        }

        if (CollectionUtils.isEmpty(privilegeUrls)) {
            privilegeUrls = new ArrayList<>();
            List<Integer> userRoleList = userService.findUserRoleColumnRoleIdByUserId(userId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<Integer> roleAccessList = new ArrayList<>();
                for (Integer roleId : userRoleList) {
                    List<Integer> findCurrentRoleAccess = roleService.findRoleAccessColumnAccessIdByRoleId(roleId);
                    if (CollectionUtils.isNotEmpty(findCurrentRoleAccess)) {
                        roleAccessList.addAll(findCurrentRoleAccess);
                    }
                }
                //获取集合权限对应的链接
                if (CollectionUtils.isNotEmpty(roleAccessList)) {
                    for (Integer accessId : roleAccessList) {
                        Access access = accessService.findAccessOne(accessId);
                        if (null != access) {
                            List<String> privilegeUrlList = new ArrayList<>();
                            String urls = access.getUrls();
                            privilegeUrlList = mergePrivilegeUrls(urls);
                            privilegeUrls.addAll(privilegeUrlList);
                        }
                    }
                }
            }
        }

        return privilegeUrls;
    }

    private List<String> mergePrivilegeUrls(String urls) {
        List<String> allUrls = (List<String>) JsonUtils.fromJson(urls, List.class);
        return allUrls;
    }


    /**
     * 判断登录状态
     * @param request
     * @return
     */
    private boolean checkoutLoginStatus(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            LOG.info("Checkout cookies is null");
            return false;
        }
        String secretKey = "";
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("SecretKey")) {
                secretKey = cookie.getValue();
                break;
            }
        }

        //如果对于cookie不存在
        if (StringUtils.isEmpty(secretKey)) {
            LOG.info("Checkout login status fail, fail result: searetKey is null");
            return false;
        }

        String[] splitSecretKey = StringUtils.split(secretKey, "#");
        String autoToken = splitSecretKey[0];
        String userId = splitSecretKey[1];
        user = userService.findUserOne(Integer.valueOf(userId));
        if (null == user) {
            LOG.info("Checkout login status, find user is null");
            return false;
        }

        String createAutoToken = createAutoToken(user, request);
        if (!autoToken.equals(createAutoToken)) {
            LOG.info("Checkout login status, auto token not equals");
            return false;
        }

        LOG.info("Checkout Login status: " + secretKey + " success");
        return true;
    }

    private String createAutoToken(User user, HttpServletRequest request) {
        String encode = user.getId() + user.getName() + user.getEmail() + request.getHeader("user-agent");
        return MD5Utils.encoder(encode);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
