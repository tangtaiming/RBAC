package com.rbac.application.action.core;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.commons.CommonsLogger;
import com.rbac.application.orm.Access;
import com.rbac.application.orm.RoleAccess;
import com.rbac.application.service.AccessService;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import com.system.core.session.PageSession;
import com.system.core.session.RbacSession;
import com.system.core.vo.NavigatorRsVo;
import com.system.util.base.JsonUtils;
import com.system.util.base.PageUtils;
import nu.xom.ParsingException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @auther ttm
 * @date 2018/7/31
 */
public class RbacAction<E> extends ActionSupport {

    /**
     * 导航菜单
     */
    private NavigatorRsVo nav;

    /**
     * 登录用户
     */
    private String loginName;

    /**
     * 获取数据
     */
    private List<?> dataList;

    private String time;

    private String id;

    /**
     * 结果json
     */
    private String result;

    private static Set<String> chooseAccessList;

    public void _execute() {
        RbacSession rbacSession = new RbacSession();
        Map<String, Object> session = rbacSession.getSession();
        if (!(null == session)) {
            String loginName = (String) session.get("name");
            setLoginName(loginName);
            String secretKey = (String) session.get("secretKey");
            if (!StringUtils.isEmpty(secretKey)) {
                String[] splitSecretKey = StringUtils.split(secretKey, "#");
                String userId = splitSecretKey[1];
                //根据用户查询用户对于的组，
                //根据用户组查下组下面的权限
                UserService userService = new UserService();
                List<Integer> userRoleIdList = userService.findUserRoleColumnRoleIdByUserId(Integer.valueOf(userId));
                if (!CollectionUtils.isEmpty(userRoleIdList) && CollectionUtils.isEmpty(chooseAccessList)) {
                    chooseAccessList = new HashSet<>();
                    RoleService roleService = new RoleService();
                    for (Integer userRoleIdRow : userRoleIdList) {
                        List<RoleAccess> roleAccessList = roleService.findRoleAccessByRoleId(userRoleIdRow);
                        if (!(CollectionUtils.isEmpty(roleAccessList))) {
                            AccessService accessService = new AccessService();
                            for (RoleAccess roleAccessRow : roleAccessList) {
                                Access access = accessService.findAccessOne(roleAccessRow.getAccessId());
                                if (!(null == access)) {
                                    String uri = access.getUrls();
                                    List<String> uriList = (List<String>) JsonUtils.fromJson(uri, List.class);
                                    chooseAccessList.addAll(uriList);
                                }
                            }
                        }
                    }
                    LOG.info("access uri: " + chooseAccessList.toString());
                }
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

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
