package com.rbac.application.action.orm;

import com.rbac.application.orm.Role;

/**
 * 角色 简单对象
 * @auther ttm
 * @date 2018/8/3
 */
public class RoleOrm {

    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    public RoleOrm(Role role) {
        id = role.getId();
        name = role.getName();
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
