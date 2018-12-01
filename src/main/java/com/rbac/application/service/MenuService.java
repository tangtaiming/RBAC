package com.rbac.application.service;

import com.rbac.application.action.vo.DeleteMenuRsVo;
import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.action.vo.ValidateSaveMenuRsVo;
import com.rbac.application.dao.MenuDao;
import com.rbac.application.orm.Menu;
import com.system.core.exception.RbacException;
import com.system.util.base.JsonUtils;
import com.system.util.base.PageUtils;
import com.system.util.enumerate.MenuType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuService extends SimpleCoreService<Menu> {

    private Logger LOG = LoggerFactory.getLogger(MenuService.class);

    private MenuDao menuDao = new MenuDao();

    public static final Long ROOTID = 0L;

    public static final String ROOTNAME = "一级菜单";

    public List<Menu> findMenuAllList() {
        return menuDao.findAllList();
    }

    public int findMenuAllListCount() {
        return menuDao.findAllListCount();
    }

    public SaveMenuReVo createMenu() {
        SaveMenuReVo saveMenuReVo = new SaveMenuReVo();
        saveMenuReVo.setParentId(ROOTID);
        saveMenuReVo.setParentName(ROOTNAME);
        return saveMenuReVo;
    }

    public SaveMenuReVo editMenu(String id) {
        Menu menu = menuDao.findOne(Long.valueOf(id));
        SaveMenuReVo saveMenuReVo = new SaveMenuReVo();
        if (!(null == menu)) {
            saveMenuReVo.setId(menu.getId());
            saveMenuReVo.setName(menu.getName());
//            saveMenuReVo.setParentId(menu.getParentId());
//            saveMenuReVo.setParentName(menu.getParentName());
            saveMenuReVo.setUrl(menu.getUrl());
            saveMenuReVo.setIcon(menu.getIcon());
            saveMenuReVo.setType(menu.getType());
            Menu parentMenu = menuDao.findOne(Long.valueOf(menu.getParentId()));
            if (!(null == parentMenu)) {
                saveMenuReVo.setParentId(parentMenu.getId());
                saveMenuReVo.setParentName(parentMenu.getName());
            } else if (null == menu.getParentId() || 0 == menu.getParentId()) {
                saveMenuReVo.setParentId(ROOTID);
                saveMenuReVo.setParentName(ROOTNAME);
            }
        }

        return saveMenuReVo;
    }

    /**
     * 根据 父ID查询菜单
     * @param parentId
     * @return
     */
    public List<Menu> fetchMenuByParentId(Long parentId) {
        Map<String, Object> query = new HashMap<>();
        query.put("parentId", parentId);
        List<Menu> menuList = menuDao.findEqList(query);

        return menuList;
    }

    public List<Menu> fetchMenuByParentId(Long parentId, List<Long> menuIdList) {
        List<Menu> menuList = fetchMenuByParentId(parentId);
        if (null == menuIdList) {
            return menuList;
        }

        List<Menu> userMenuList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menuIdList.contains(menu.getId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    public List<Menu> fetchMenuAllList(List<Long> menuIdList) {
        //查询根菜单列表
        List<Menu> rootMenuList = fetchMenuByParentId(ROOTID, menuIdList);
        //递归获取子菜单
        fetchMenuTreeList(rootMenuList, menuIdList);

        return rootMenuList;
    }

    public List<Menu> fetchUserMenuList(Integer userId) {


        return null;
    }

    /**
     * 递归
     * @param menuList
     * @param menuIdList
     * @return
     */
    private List<Menu> fetchMenuTreeList(List<Menu> menuList, List<Long> menuIdList) {
        List<Menu> subMenuList = new ArrayList<>();

        for (Menu menu : menuList) {
            if (menu.getType() == MenuType.DIRECTORY.getType()) { //目录
                menu.setList(fetchMenuTreeList(fetchMenuByParentId(menu.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(menu);
        }
        return subMenuList;
    }

    public String fetchMenuByParentId(String id) {
        List<Menu> oneMenuList = new ArrayList<>();
        Integer parentId = Integer.valueOf(id);
        if (parentId == -1) {
            Map<String, Object> query = new HashMap<>();
            query.put("parentId", 0);
            //默认添加顶级类目
            SaveMenuRsVo rootMenu = new SaveMenuRsVo();
            rootMenu.setId(ROOTID);
            rootMenu.setName(ROOTNAME);
            rootMenu.setParentId(-1L);
            rootMenu.setIsParent("true");
            rootMenu.setNocheck(false);
            oneMenuList = menuDao.findEqList(query);
            List<SaveMenuRsVo> oneMenuRsList = new ArrayList<>();
            oneMenuRsList.add(0, rootMenu);
            if (!CollectionUtils.isEmpty(oneMenuList)) {
                for (Menu menu : oneMenuList) {
                    SaveMenuRsVo oneMenuRs = new SaveMenuRsVo();
                    oneMenuRs.setId(menu.getId());
                    oneMenuRs.setName(menu.getName());
                    oneMenuRs.setParentId(menu.getParentId());
                    oneMenuRs.setParentName(menu.getParentName());
                    oneMenuRs.setIsParent("true");
                    oneMenuRsList.add(oneMenuRs);
                }
            }
            return JsonUtils.toJson(oneMenuRsList);
        } else {
            Map<String, Object> query = new HashMap<>();
            query.put("parentId", parentId);
            oneMenuList = menuDao.findEqList(query);
            List<SaveMenuRsVo> oneMenuRsList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(oneMenuList)) {
                for (Menu menu : oneMenuList) {
                    if (!(menu.getType() == MenuType.BUTTON.getType())) {
                        SaveMenuRsVo oneMenuRs = new SaveMenuRsVo();
                        oneMenuRs.setId(menu.getId());
                        oneMenuRs.setName(menu.getName());
                        oneMenuRs.setParentId(menu.getParentId());
                        oneMenuRs.setParentName(menu.getParentName());
                        oneMenuRs.setIsParent("true");
                        oneMenuRsList.add(oneMenuRs);
                    }
                }
            }

            return JsonUtils.toJson(oneMenuRsList);
        }
    }

    /**
     * 获取一级类别
     * @return
     */
    public SaveMenuRsVo findOneMenuList() {
        SaveMenuRsVo saveMenuRsVo = new SaveMenuRsVo();
//        Map<String, Object> query = new HashMap<>();
//        query.put("parentId", 0);
//        SaveMenuRsVo rootMenu = new SaveMenuRsVo();
//        //默认添加顶级类目
//        rootMenu.setId(ROOTID);
//        rootMenu.setName(ROOTNAME);
//        rootMenu.setParentId(-1L);
//        rootMenu.setIsParent("true");
//        List<Menu> oneMenuList = menuDao.findEqList(query);
//        List<SaveMenuRsVo> oneMenuRsList = new ArrayList<>();
//        oneMenuRsList.add(0, rootMenu);
//        if (!CollectionUtils.isEmpty(oneMenuList)) {
//            for (Menu menu : oneMenuList) {
//                SaveMenuRsVo oneMenuRs = new SaveMenuRsVo();
//                oneMenuRs.setId(menu.getId());
//                oneMenuRs.setName(menu.getName());
//                oneMenuRs.setParentId(menu.getParentId());
//                oneMenuRs.setParentName(menu.getParentName());
//                if (!(menu.getType() == MenuType.BUTTON.getType())) {
//                    oneMenuRs.setIsParent("true");
//                } else {
//                    oneMenuRs.setIsParent("false");
//                }
//                oneMenuRsList.add(oneMenuRs);
//            }
//        }
//        saveMenuRsVo.setMenuList(oneMenuRsList);
        return saveMenuRsVo;
    }

//    public SaveMenuRsVo findNoButtonMenuList() {
//        SaveMenuRsVo saveMenuRsVo = new SaveMenuRsVo();
//        List<Menu> menuList = menuDao.queryNotButtonMenuList();
//        //默认添加顶级类目
//        Menu root = new Menu();
//        root.setId(ROOTID);
//        root.setName(ROOTNAME);
//        root.setParentId(-1L);
//        root.setOpen(true);
//        menuList.add(0, root);
//
//        saveMenuRsVo.setMenuList(menuList);
//        return saveMenuRsVo;
//    }

    public boolean saveMenu(SaveMenuReVo menuReVo) {
        Long menuId = menuReVo.getId();
        if (null == menuId) {
            Menu menu = new Menu();
            menu.setName(menuReVo.getName());
            menu.setUrl(menuReVo.getUrl());
            menu.setIcon(menuReVo.getIcon());
            menu.setType(menuReVo.getType());
            menu.setParentId(menuReVo.getId());
            menu.setParentName(menuReVo.getParentName());
            menu.setParentId(menuReVo.getParentId());
            menu.setParentName(menuReVo.getParentName());
            menu.setOrderNum(0);
            menu.setPerms("");
            Long createMenuId = (Long) menuDao.save(menu);
            return (null == createMenuId) ? false : true;
        } else {
            Menu findMenu = menuDao.findOne(menuId);
            if (null == findMenu) {
                return false;
            }
            findMenu.setType(menuReVo.getType());
            findMenu.setName(menuReVo.getName());
            findMenu.setParentId(menuReVo.getParentId());
            findMenu.setUrl(menuReVo.getUrl());
            findMenu.setIcon(menuReVo.getIcon());
            findMenu.setPerms("");
            findMenu.setOrderNum(0);
            menuDao.update(findMenu);
        }
        return true;
    }

    public SaveMenuRsVo findEditPageData(Serializable menuId) {
        Menu menu = menuDao.findOne(Long.valueOf((String) menuId));
        String parentName = "";
        if (!(null == menu)) {
            Long parentId = menu.getParentId();
            if (parentId == ROOTID) {
                parentName = ROOTNAME;
            } else {
                Menu parentMenu = menuDao.findOne(menu.getParentId());
                if (!(null == parentMenu)) {
                    parentName = parentMenu.getName();
                    LOG.info("Parent menu, menu id: {}, name: {}", parentMenu.getId(), parentName);
                }
            }
        }
        menu.setParentName(parentName);
        //加入 菜单
//        SaveMenuRsVo saveMenuRsVo = findNoButtonMenuList();
        return null;
//                new SaveMenuRsVo(menu, saveMenuRsVo.getMenuList());
    }

    public ValidateSaveMenuRsVo validateSaveMenuData(SaveMenuReVo saveMenuReVo) {
        ValidateSaveMenuRsVo validateVo = new ValidateSaveMenuRsVo();
        if (null == saveMenuReVo.getId()) {
            Map<String, Object> typeAndNameQuery = new LinkedMap();
            typeAndNameQuery.put("type", saveMenuReVo.getType());
            typeAndNameQuery.put("name", saveMenuReVo.getName());
            Menu menu = null;
            try {
                menu = menuDao.findEqOne(typeAndNameQuery);
            } catch (RbacException e) {
                return (ValidateSaveMenuRsVo) validateVo.fail("菜单已经存在,并且有多个菜单数据,数据异常请删除异常数据");
            } catch (Exception ex) {
                LOG.error("Error result: {}", ex);
                return (ValidateSaveMenuRsVo) validateVo.error("未知异常,请联系管理员");
            }
            if (!(null == menu)) {
                return (ValidateSaveMenuRsVo) validateVo.fail("菜单已经存在,请进行菜单名称修改");
            }
        }

        return validateVo;
    }

    public List<Menu> findMenuList(Map<String, Object> query) {
        return menuDao.findEqList(query);
    }

    public Menu findMenuOne(Map<String, Object> query) {
        try {
            return menuDao.findEqOne(query);
        } catch (RbacException e) {
            e.printStackTrace();
            LOG.error("Error: {}", e.getMessage());
        }

        return null;
    }

    public Menu findMenuOne(Long id) {
        return menuDao.findOne(id);
    }

    public String deleteMenu(String menuId) {
        LOG.warn("Delete {} success", menuId);
        Menu deleteMenu = menuDao.findOne(Long.valueOf(menuId));
        DeleteMenuRsVo menuRsVo = new DeleteMenuRsVo();
        if (!(null == deleteMenu)) {
            return menuDao.delete(deleteMenu) ? menuRsVo.success() : menuRsVo.menuFailMenu("删除菜单失败!");
        }

        return menuRsVo.menuFailMenu("删除菜单失败!");
    }

    @Override
    public List<Menu> getDataList() {
        return menuDao.findDataList();
    }

    @Override
    public PageUtils getPage() {
        return menuDao.findPage();
    }

    public List<Menu> findNotEqButtonMenu() {
        Integer button = MenuType.BUTTON.getType();
        Map<String, Object> query = new HashMap<>();
        query.put("type", button);
        List<Menu> menuList = menuDao.findNotEqList(query);
        return menuList;
    }

}
