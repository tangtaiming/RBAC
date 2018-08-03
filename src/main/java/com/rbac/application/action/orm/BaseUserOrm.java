package com.rbac.application.action.orm;

import com.rbac.application.orm.Role;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class BaseUserOrm {

    private List<RoleOrm> roleList;

    public BaseUserOrm() {}

    public List<RoleOrm> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleOrm> roleList) {
        this.roleList = roleList;
    }

    public void putRoleOrmList(List<Role> roleList) {
        if (CollectionUtils.isNotEmpty(roleList)) {
            this.roleList = new ArrayList<>();
            for (Role role : roleList) {
                RoleOrm roleOrm = new RoleOrm(role);
                this.roleList.add(roleOrm);
            }
        }
    }

}
