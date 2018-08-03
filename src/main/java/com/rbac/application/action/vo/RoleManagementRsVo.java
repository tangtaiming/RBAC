package com.rbac.application.action.vo;

import com.rbac.application.orm.Role;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class RoleManagementRsVo {

    private Integer id;

    private String name;

    public RoleManagementRsVo(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
