package com.system.core.page.checkbox;

import com.rbac.application.orm.Role;
import com.rbac.application.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/11/6 0006
 **/
public class RoleSelect {

    private RoleService roleService = new RoleService();

    public Map<String, Object> getOption() {
        Map<String, Object> option = new HashMap<>();
        List<Role> roleList = roleService.findRoleList();
        for (Role role : roleList) {
            option.put(role.getId().toString(), role.getName());
        }
        return option;
    }

}
