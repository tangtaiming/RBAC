package com.rbac.application.service;

import com.rbac.application.dao.RoleMenuDao;
import com.rbac.application.orm.RoleMenu;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleMenuService {

   private RoleMenuDao roleMenuDao = new RoleMenuDao();

   public List<Long> findMenuIdByRoleId(Integer roleId) {
       List<Long> menuIdList = new ArrayList<>();
       List<RoleMenu> roleMenuList = findRoleMenuByRoleId(roleId);
       if (!CollectionUtils.isEmpty(roleMenuList)) {
           for (RoleMenu roleMenu : roleMenuList) {
               menuIdList.add(roleMenu.getMenuId());
           }
       }

       return menuIdList;
   }

    /**
     * 角色id 查下对应 角色与菜单关系
     * @param roleId
     * @return
     */
   public List<RoleMenu> findRoleMenuByRoleId(Integer roleId) {
       Map<String, Object> query = new HashMap<>();
       query.put("roleId", roleId);
       List<RoleMenu> roleMenuList = roleMenuDao.findEqList(query);
       return roleMenuList;
   }

   public boolean deleteRoleMenu(RoleMenu roleMenu) {
       if (!(null == roleMenu) && !(null == roleMenu.getId())) {
           return deleteRoleMenu(roleMenu.getId());
       }
       return false;
   }

   public boolean deleteRoleMenu(Integer id) {
       RoleMenu roleMenu = roleMenuDao.findOne(id);
       if (!(null == roleMenu)) {
           roleMenuDao.delete(roleMenu);
           return true;
       }
       return false;
   }

   public boolean saveRoleMenu(RoleMenu roleMenu) {
       roleMenuDao.save(roleMenu);
       return !(null == roleMenu.getId()) ? true : false;
   }

}
