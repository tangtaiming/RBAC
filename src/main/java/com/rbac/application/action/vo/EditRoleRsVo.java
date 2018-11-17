package com.rbac.application.action.vo;

import com.rbac.application.orm.Role;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class EditRoleRsVo {

    private Integer id;

    private String name;

    public EditRoleRsVo() {
    }

    public EditRoleRsVo(Role role) {
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
