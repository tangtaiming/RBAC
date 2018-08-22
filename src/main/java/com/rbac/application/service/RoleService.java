package com.rbac.application.service;

import com.rbac.application.action.vo.RoleManagementRsVo;
import com.rbac.application.action.vo.SaveRoleReVo;
import com.rbac.application.action.vo.SaveSiteAccessReVo;
import com.rbac.application.dao.RoleAccessDao;
import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.RoleAccess;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    private List<Role> roleList = new ArrayList<>();

    private RoleDao roleDao = new RoleDao();

    private RoleAccessDao roleAccessDao = new RoleAccessDao();

    /**
     * 获取角色集合
     * @return
     */
    public List<Role> findRoleList() {
        roleList = roleDao.findAllList();
        LOG.info("Find role size: " + roleList.size());
        return roleList;
    }

    public boolean saveRole(SaveRoleReVo role) {
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
            Role createRole = new Role();
            createRole.setCreateDate(currentTime);
            createRole.setUpdateDate(currentTime);
            createRole.setStatus(1);
            createRole.setName(role.getName());
            roleDao.save(createRole);
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

    public int findRoleAllListCount() {
        return roleDao.findAllListCount();
    }

    public boolean saveSiteAccess(SaveSiteAccessReVo siteAccessReVo) {
        //查询出对应这个角色的所有角色权限关系
        Integer roleId = siteAccessReVo.getRoleId();
        List<Integer> newRoleAccessList = Optional.ofNullable(siteAccessReVo.getAccessId()).orElse(new ArrayList<>());
        List<RoleAccess> roleAccessList = findRoleAccessByRoleId(roleId);
        List<Integer> oldRoleAccessList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleAccessList)) {
            for (RoleAccess roleAccess : roleAccessList) {
                Integer tmpAccessId = roleAccess.getAccessId();
                oldRoleAccessList.add(tmpAccessId);
                if (!newRoleAccessList.contains(tmpAccessId)) {
                    boolean deleteFalg = roleAccessDao.delete(roleAccess);
                    LOG.info("Delete user role relation id: " + tmpAccessId + (deleteFalg ? " success" : " fail"));
                }
            }
        }

        if (CollectionUtils.isNotEmpty(newRoleAccessList)) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            for (Integer roleAccessId : newRoleAccessList) {
                if (!oldRoleAccessList.contains(roleAccessId)) {
                    RoleAccess createRoleAccess = new RoleAccess();
                    createRoleAccess.setRoleId(roleId);
                    createRoleAccess.setAccessId(roleAccessId);
                    createRoleAccess.setCreateDate(time);
                    Integer createRoleAccessId = roleAccessDao.save(createRoleAccess);
                    LOG.info("Save user role relation id: " + createRoleAccessId + ((null != createRoleAccessId) ? " success" : " fail"));
                }
            }
        }
        return true;
    }

    public List<RoleManagementRsVo> findRoleManagementRsVo() {
        List<Role> roleList = roleDao.findAllList();
        List<RoleManagementRsVo> roleRsVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (Role role : roleList) {
                RoleManagementRsVo roleRsVo = new RoleManagementRsVo(role);
                roleRsVoList.add(roleRsVo);
            }
        }

        return roleRsVoList;
    }

    public List<RoleAccess> findRoleAccessByRoleId(Integer roleId) {
        return roleAccessDao.findRoleAccessByRoleId(roleId);
    }

    public List<Integer> findRoleAccessColumnAccessIdByRoleId(Integer roleId) {
        return roleAccessDao.findRoleAccessColumnAccessIdByRoleId(roleId);
    }

}
