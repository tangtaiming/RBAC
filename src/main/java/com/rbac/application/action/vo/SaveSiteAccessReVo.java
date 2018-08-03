package com.rbac.application.action.vo;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class SaveSiteAccessReVo {

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

}
