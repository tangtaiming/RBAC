package com.system.core.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rbac.application.orm.Menu;
import com.rbac.application.orm.RoleMenu;
import com.rbac.application.orm.User;
import com.rbac.application.service.*;
import com.system.core.session.RbacSession;
import com.system.util.base.AutoTokenUtils;
import com.system.util.base.JsonUtils;
import com.system.util.enumerate.MenuType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @auther ttm
 * @date 2018/7/23
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private List<String> allowRequestUrl;

    private List<String> ignoreRequestUrl;

    private final static String ERROR_PAGE = "error";

    private final static String LOGIN_PAGE = "login";

    private UserService userService = new UserService();

    private RoleService roleService = new RoleService();

    private AccessService accessService = new AccessService();

    private RoleMenuService roleMenuService = new RoleMenuService();

    private MenuService menuService = new MenuService();

    private User user;

    public AuthenticationInterceptor() {
        initAllowRequestUrl();
    }

    public List<String> privilegeUrls;

    private static final String propertyName = "zh";

    /**
     * 初始化允许请求的URL
     */
    private void initAllowRequestUrl() {
        allowRequestUrl = new ArrayList<>();
        allowRequestUrl.add("/admin/vlogin");
        allowRequestUrl.add("/admin/signOut");
        allowRequestUrl.add("/admin/login");
        allowRequestUrl.add("/admin/doLogin");
        allowRequestUrl.add("/test/page");
        allowRequestUrl.add("/test/page2");
        allowRequestUrl.add("/test/savePage2");

        ignoreRequestUrl = new ArrayList<>();
        ignoreRequestUrl.add("/admin/vlogin");
        ignoreRequestUrl.add("/admin/signOut");
        ignoreRequestUrl.add("/admin/login");
        ignoreRequestUrl.add("/admin/doLogin");
        ignoreRequestUrl.add("/test/page");
        ignoreRequestUrl.add("/test/page2");
        ignoreRequestUrl.add("/test/savePage2");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        ActionContext actionContext = actionInvocation.getInvocationContext();
        Locale locale = actionContext.getLocale();
        if (null == locale) {
            locale = new Locale("zh", "CN");
            actionContext.setLocale(locale);
            actionContext.getSession().put("WW_TRANS_I18N_LOCALE", actionContext.getLocale());
        }
        LOG.info("Show requestUri: {}, contextPath: {}, method: {}", requestUri, contextPath, method);
        //判断对应的请求url 是否符合
        if (!(checkoutAllowRequestUrl(requestUri)) && !checkoutLoginStatusBySession(request)) {
            return LOGIN_PAGE;
        }
        //判断是否拥有对应链接权限
        if (!checkPrivilege(requestUri)) {
            return ERROR_PAGE;
        }

        return actionInvocation.invoke();
    }

    private boolean checkoutAllowRequestUrl(String requestUri) {
        String[] url = StringUtils.split(requestUri, ";");
        if (null == url) {
            return false;
        }

        return allowRequestUrl.contains(url[0]);
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

        return inArray(url, findRolePrivilege(user.getId()));
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
//                List<String> accessUrlList = new ArrayList<>();
                List<Long> menuIdList = new ArrayList<>();
                for (Integer roleId : userRoleList) {
                    List<RoleMenu> roleMenuList = roleMenuService.findRoleMenuByRoleId(roleId);
                    if (!CollectionUtils.isEmpty(roleMenuList)) {
                        for (RoleMenu roleMenu : roleMenuList) {
                            Menu menu = menuService.findMenuOne(roleMenu.getMenuId());
                            if (!(null == menu) && !(menu.getType() == MenuType.DIRECTORY.getType())) {
                                LOG.info("User url: " + menu.getUrl());
                                privilegeUrls.add(menu.getUrl());
                            }
                            if (!(null == menu)) {
                                menuIdList.add(menu.getId());
                            }
                        }
                    }
                }

                RbacSession session = new RbacSession();
                session.put("privilegeMenu", JsonUtils.toJson(menuIdList));
            }
        }

        return privilegeUrls;
    }

    private List<String> mergePrivilegeUrls(String urls) {
        List<String> allUrls = (List<String>) JsonUtils.fromJson(urls, List.class);
        return allUrls;
    }


    private boolean checkoutLoginStatusBySession(HttpServletRequest request) {
        RbacSession session = new RbacSession();
        if (null == session) {
            LOG.info("Checkout session is null");
            return false;
        }

        String secretKey = (String) session.get("secretKey");
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

        String createAutoToken = AutoTokenUtils.createAutoToken(user, request);
        if (!autoToken.equals(createAutoToken)) {
            LOG.info("Checkout login status, auto token not equals");
            return false;
        }

        LOG.info("Checkout Login status: " + secretKey + " success");
        return true;
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

        String createAutoToken = AutoTokenUtils.createAutoToken(user, request);
        if (!autoToken.equals(createAutoToken)) {
            LOG.info("Checkout login status, auto token not equals");
            return false;
        }

        LOG.info("Checkout Login status: " + secretKey + " success");
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
