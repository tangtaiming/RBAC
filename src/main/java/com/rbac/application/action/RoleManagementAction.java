package com.rbac.application.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rbac.application.orm.Role;
import com.rbac.application.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleManagementAction extends ActionSupport {

    private static final Logger LOG = LoggerFactory.getLogger(RoleManagementAction.class);

    private RoleService roleService = new RoleService();

    private List<Role> roleList;

    private Role role;

    private String result;

    private Integer id;

    private static final String ERROR_KEY = "error";

    /**
     * 角色管理
     * @return
     */
    public String roleManagement() {
        List<Role> roleList = roleService.findRoleList();
        setRoleList(roleList);
        return SUCCESS;
    }

    /**
     * 创建角色
     * @return
     */
    public String createRole() {
        return SUCCESS;
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
        Role role = roleService.findRoleOne(getId());
        if (null == role) {
            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
            return INPUT;
        }

        setRole(role);
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
}
