package com.rbac.application.orm;

import java.io.Serializable;

/**
 * 角色与菜单之间关系
 * @auther ttm
 * @date 2018/11/25 0025
 **/
public class RoleMenu implements Serializable {

    private Integer id;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 角色ID
     */
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
