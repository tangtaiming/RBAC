package com.rbac.application.action.vo;

import com.rbac.application.orm.User;

/**
 * @auther ttm
 * @date 2018/8/3 0003
 **/
public class UserManagementRsVo {

    private Integer id;

    private String name;

    private String email;

    public UserManagementRsVo(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
