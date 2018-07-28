package com.rbac.application.service;

import com.rbac.application.action.dto.RoleDto;
import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Role;
import org.apache.commons.collections.CollectionUtils;
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

    private List<Role> roleList = new ArrayList<>();

    private RoleDao roleDao = new RoleDao();

    /**
     * 获取角色集合
     * @return
     */
    public List<Role> findRoleList() {
        roleList = roleDao.findAllList();
        LOG.info("Find role size: " + roleList.size());
        return roleList;
    }

    public boolean saveRole(Role role) {
        Integer roleId = role.getId();
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        if (null != roleId) {
            Role findRole = findRoleOne(roleId);
            if (null != findRole) {
                findRole.setName(role.getName());
                findRole.setUpdateDate(currentTime);
                roleDao.update(findRole);
                return true;
            }
        } else {
            role.setId((roleList.size() + 1));
            role.setCreateDate(currentTime);
            role.setUpdateDate(currentTime);
            role.setStatus(1);
            roleDao.save(role);
            return true;
        }

        return false;
    }

    public Role findRoleOne(Integer rid) {
        Role role = roleDao.findOne(rid);
        return role;
    }

    public Role findRoleByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }

    public List<RoleDto> findRoleDtoList() {
        List<RoleDto> roleDtoList = new ArrayList<>();
        List<Role> roleList = findRoleList();
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (Role role : roleList) {
                RoleDto createRoleDto = new RoleDto();
                createRoleDto.setId(role.getId());
                createRoleDto.setName(role.getName());
                roleDtoList.add(createRoleDto);
            }
        }
        return roleDtoList;
    }

}
