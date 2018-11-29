package com.rbac.application.service;

import com.rbac.application.action.orm.MenuOrm;
import com.rbac.application.action.vo.EditRoleRsVo;
import com.rbac.application.action.vo.RoleManagementRsVo;
import com.rbac.application.action.vo.SaveRoleReVo;
import com.rbac.application.action.vo.SaveSiteAccessReVo;
import com.rbac.application.dao.RoleAccessDao;
import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Menu;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.RoleAccess;
import com.rbac.application.orm.RoleMenu;
import com.system.util.base.JsonUtils;
import com.system.util.base.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleService extends SimpleCoreService<Role> {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    private List<Role> roleList = new ArrayList<>();

    private RoleDao roleDao = new RoleDao();

    private RoleAccessDao roleAccessDao = new RoleAccessDao();

    private MenuService menuService = new MenuService();

    private RoleMenuService roleMenuService = new RoleMenuService();

    private String toMenuJson(List<Long> choseMenu) {
        List<Menu> menuList = menuService.findMenuAllList();
        List<MenuOrm> menuOrmList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                MenuOrm menuOrm = new MenuOrm();
                Long menuId = menu.getId();
                menuOrm.setId(menuId);
                menuOrm.setName(menu.getName());
                menuOrm.setParentId(menu.getParentId());
                menuOrm.setIsParent("true");
                if (choseMenu.contains(menuId)) {
                    menuOrm.setChecked("true");
                } else {
                    menuOrm.setChecked("false");
                }
                menuOrmList.add(menuOrm);
            }
        }
        return JsonUtils.toJson(menuOrmList);
    }

    public EditRoleRsVo createRole() {
        EditRoleRsVo editRoleRsVo = new EditRoleRsVo();
        editRoleRsVo.setMenuJson(toMenuJson(new ArrayList<>()));
        return editRoleRsVo;
    }

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
        boolean saveRoleFalg = false;
        if (null != roleId) {
            Role findRole = findRoleOne(roleId);
            if (null != findRole) {
                findRole.setName(role.getName());
                findRole.setUpdateDate(currentTime);
                roleDao.update(findRole);
                saveRoleFalg = true;
            }
        } else {
            Role createRole = new Role();
            createRole.setCreateDate(currentTime);
            createRole.setUpdateDate(currentTime);
            createRole.setStatus(1);
            createRole.setName(role.getName());
            roleDao.save(createRole);
            roleId = createRole.getId();
            saveRoleFalg = true;
        }

        if (saveRoleFalg) {
            //当前角色与菜单
            List<Long> currentMenuIdList = role.getMenuIdList();
            /**
             * 找出删除的角色
             * 假如已有的角色集合是A，界面传递过得角色集合是B
             * 角色集合A当中的某个角色不在角色集合B当中，就应该删除
             */
            List<RoleMenu> oldRoleMenuList = roleMenuService.findRoleMenuByRoleId(roleId);
            List<Long> oldMenuIdList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(oldRoleMenuList)) {
                for (RoleMenu roleMenu : oldRoleMenuList) {
                    Long oldMenuId = roleMenu.getMenuId();
                    oldMenuIdList.add(oldMenuId);
                    if (!currentMenuIdList.contains(oldMenuId)) {
                        roleMenuService.deleteRoleMenu(roleMenu);
                        LOG.info("Delete id: " + roleMenu.getId() + " menuId: " + oldMenuId + " roleId: " + roleMenu.getRoleId() + " success");
                    }
                }

            }

            /**
             * 找出添加的角色
             * 假如已有的角色集合是A，界面传递过得角色集合是B
             * 角色集合B当中的某个角色不在角色集合A当中，就应该添加
             */
            if (!CollectionUtils.isEmpty(currentMenuIdList)) {
                for (Long currentMenuId : currentMenuIdList) {
                    if (!oldMenuIdList.contains(currentMenuId)) {
                        RoleMenu roleMenu = new RoleMenu();
                        roleMenu.setRoleId(roleId);
                        roleMenu.setMenuId(currentMenuId);
                        roleMenuService.saveRoleMenu(roleMenu);
                        LOG.info("Save id: " + roleMenu.getId() + " menuId: " + currentMenuId + " roleId: " + roleId + " success");
                    }
                }
            }
        }
        return saveRoleFalg;
    }

    public EditRoleRsVo findEditRoleRsVoOne(String id) {
        Role role = findRoleOne(id);
        EditRoleRsVo editRoleRsVo = null;
        if (!(null == role)) {
            List<Long> choseMenuIdList = roleMenuService.findMenuIdByRoleId(role.getId());
            editRoleRsVo = new EditRoleRsVo(role);
            editRoleRsVo.setMenuJson(toMenuJson(choseMenuIdList));
        }

        return editRoleRsVo;
    }

    public Role findRoleOne(Serializable rid) {
        System.out.println("rid: "+ rid);
        Role role = roleDao.findOne(Integer.valueOf(rid.toString()));
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
                    Integer createRoleAccessId = (Integer) roleAccessDao.save(createRoleAccess);
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

    public boolean deleteRole(String id) {
        Role role = findRoleOne(id);
        boolean deleteRoleFalg = false;
        if (!(null == role)) {
            deleteRoleFalg = roleDao.delete(role);
            LOG.info("Delete role id:" + role.getId() + " role name: " + role.getName() + " result: " + getResult(deleteRoleFalg));
            List<RoleMenu> roleMenuList = roleMenuService.findRoleMenuByRoleId(role.getId());
            if (!CollectionUtils.isEmpty(roleMenuList)) {
                for (RoleMenu roleMenu : roleMenuList) {
                    roleMenuService.deleteRoleMenu(roleMenu);
                    LOG.info("Delete role menu id: " + roleMenu.getId());
                }
            }
        }

        return deleteRoleFalg;
    }


    @Override
    public List<Role> getDataList() {
        return roleDao.findDataList();
    }

    @Override
    public PageUtils getPage() {
        return roleDao.findPage();
    }
}
