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

    private static final Long ROOTID = 0L;

    private static final String ROOTNAME = "一级菜单";

    public List<Menu> findMenuAllList() {
        return menuDao.findAllList();
    }

    public int findMenuAllListCount() {
        return menuDao.findAllListCount();
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
            rootMenu.setNocheck(true);
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
        Long menuId = menuReVo.getMenuId();
        if (null == menuId) {
            Menu menu = new Menu();
            menu.setName(menuReVo.getName());
            menu.setUrl(menuReVo.getUrl());
            menu.setPerms(menuReVo.getPerms());
            menu.setIcon(menuReVo.getIcon());
            menu.setType(menuReVo.getType());
            menu.setOrderNum(menuReVo.getOrderNum());
            menu.setParentId(menuReVo.getParentId());
            menu.setParentName(menuReVo.getParentName());
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
            findMenu.setPerms(menuReVo.getPerms());
            findMenu.setOrderNum(menuReVo.getOrderNum());
            findMenu.setIcon(menuReVo.getIcon());
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
        if (null == saveMenuReVo.getMenuId()) {
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
}
