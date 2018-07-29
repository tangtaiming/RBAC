package com.rbac.application.action.dto;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/29 0029
 **/
public class SaveSiteAccessRqDto {

    private Integer roleId;

    private List<Integer> accessId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getAccessId() {
        return accessId;
    }

    public void setAccessId(List<Integer> accessId) {
        this.accessId = accessId;
    }

    @Override
    public String toString() {
        return "SaveSiteAccessRqDto{" +
                "roleId=" + roleId +
                ", accessId=" + accessId +
                '}';
    }
}
