package com.rbac.application.service;

import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.action.vo.ValidateSaveMenuRsVo;
import com.rbac.application.dao.MenuDao;
import com.rbac.application.orm.Menu;
import com.system.core.exception.RbacException;
import org.apache.commons.collections.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuService {

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

    public SaveMenuRsVo findNoButtonMenuList() {
        SaveMenuRsVo saveMenuRsVo = new SaveMenuRsVo();
        List<Menu> menuList = menuDao.queryNotButtonMenuList();
        //默认添加顶级类目
        Menu root = new Menu();
        root.setMenuId(ROOTID);
        root.setName(ROOTNAME);
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(0, root);

        saveMenuRsVo.setMenuList(menuList);
        return saveMenuRsVo;
    }

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
                    LOG.info("Parent menu, menu id: {}, name: {}", parentMenu.getMenuId(), parentName);
                }
            }
        }
        menu.setParentName(parentName);
        //加入 菜单
        SaveMenuRsVo saveMenuRsVo = findNoButtonMenuList();
        return new SaveMenuRsVo(menu, saveMenuRsVo.getMenuList());
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
                LOG.error("Fail result: {}", e.getMessage());
                return validateVo.fail("菜单已经存在,请进行菜单名称修改");
            } catch (Exception ex) {
                LOG.error("Error result: {}", ex);
                return validateVo.error("未知异常,请联系管理员");
            }
            if (null == menu) {
                return validateVo;
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

    public boolean deleteMenu(String menuId) {
        LOG.warn("Delete {} success", menuId);
        return true;
    }

}
