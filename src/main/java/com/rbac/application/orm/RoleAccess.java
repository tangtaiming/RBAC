package com.rbac.application.orm;

import java.io.Serializable;

/**
 * @auther ttm
 * @date 2018/7/29 0029
 **/
public class RoleAccess implements Serializable {

    private Integer id;

    private Integer roleId;

    private Integer accessId;

    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
