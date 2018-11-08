package com.rbac.application.action.vo;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/11/8 0008
 **/
public class UserVo {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private List<Integer> roles;

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

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}
