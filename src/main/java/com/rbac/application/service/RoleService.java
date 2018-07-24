package com.rbac.application.service;

import com.rbac.application.orm.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    private static List<Role> roleList = new ArrayList<>();

    /**
     * 获取角色集合
     * @return
     */
    public List<Role> findRoleList() {
        LOG.info("Find role size: " + roleList.size());
        return roleList;
    }

    public boolean saveRole(Role role) {
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        role.setId(roleList.size());
        role.setCreateDate(currentTime);
        role.setUpdateDate(currentTime);
        role.setStatus(1);
        roleList.add(role);
        return true;
    }

}
