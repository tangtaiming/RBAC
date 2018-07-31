package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.dto.SaveSiteAccessRqDto;
import com.rbac.application.orm.Access;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.RoleAccess;
import com.rbac.application.service.AccessService;
import com.rbac.application.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleManagementAction extends RbacAction {

    private static final Logger LOG = LoggerFactory.getLogger(RoleManagementAction.class);

    private RoleService roleService = new RoleService();

    private AccessService accessService = new AccessService();

    private List<Role> roleList;

    private List<Access> accessList;

    private Role role;

    private String result;

    private Integer id;

    private static final String ERROR_KEY = "error";

    private SaveSiteAccessRqDto siteAccessRqDto;

    private List<RoleAccess> roleAccessList;

    /**
     * 角色管理
     * @return
     */
    public String roleManagement() {
        _execute();
        List<Role> roleList = roleService.findRoleList();
        setRoleList(roleList);
        return SUCCESS;
    }

    /**
     * 创建角色
     * @return
     */
    public String createRole() {
        _execute();
        return SUCCESS;
    }

    /**
     * 验证数据
     */
    public void validateSaveRole() {
        if (StringUtils.isEmpty(role.getName())) {
            addFieldError(ERROR_KEY, "角色名称不能为空");
            return;
        }

        Role findRole = roleService.findRoleByName(role.getName());
        if (null != findRole) {
            addFieldError(ERROR_KEY, "角色名称已经存在, 保存失败");
            return;
        }
    }

    public String saveRole() {
        LOG.info("Save role show: " + role.toString());
        roleService.saveRole(role);
        setResult("success");
        return SUCCESS;
    }

    /**
     * 编辑角色
     * @return
     */
    public String editRole() {
        _execute();
        Role role = roleService.findRoleOne(getId());
        if (null == role) {
            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
            return INPUT;
        }

        setRole(role);
        return SUCCESS;
    }

    /**
     * 设置权限
     * @return
     */
    public String siteAccess() {
        _execute();
        Role role = roleService.findRoleOne(id);
        if (null == role) {
            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
            return INPUT;
        }
        setRole(role);
        //循环获取所有权限列表
        List<Access> accessList = accessService.findAccessAllList();
        //根据角色查询角色对于权限
        List<RoleAccess> roleAccessList = roleService.findRoleAccessByRoleId(role.getId());
        setAccessList(accessList);
        setRoleAccessList(roleAccessList);
        return SUCCESS;
    }

    public String saveSiteAccess() {
        roleService.saveSiteAccess(siteAccessRqDto);
        setResult("success");
        return SUCCESS;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    public SaveSiteAccessRqDto getSiteAccessRqDto() {
        return siteAccessRqDto;
    }

    public void setSiteAccessRqDto(SaveSiteAccessRqDto siteAccessRqDto) {
        this.siteAccessRqDto = siteAccessRqDto;
    }

    public List<RoleAccess> getRoleAccessList() {
        return roleAccessList;
    }

    public void setRoleAccessList(List<RoleAccess> roleAccessList) {
        this.roleAccessList = roleAccessList;
    }
}
