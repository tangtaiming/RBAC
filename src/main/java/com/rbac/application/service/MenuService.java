package com.rbac.application.service;

import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.dao.MenuDao;
import com.rbac.application.orm.Menu;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuService {

    private Logger LOG = LoggerFactory.getLogger(MenuService.class);

    private MenuDao menuDao = new MenuDao();

    public List<Menu> findMenuAllList() {
        return menuDao.findAllList();
    }

    public int findMenuAllListCount() {
        return menuDao.findAllListCount();
    }

    public SaveMenuRsVo findNoButtonMenuList() {
        SaveMenuRsVo saveMenuRsVo = new SaveMenuRsVo();
        List<Menu> menuList = menuDao.queryNotButtonMenuList();
        if (!CollectionUtils.isEmpty(menuList)) {
            //默认添加顶级类目
            Menu root = new Menu();
            root.setMenuId(0L);
            root.setName("一级菜单");
            root.setParentId(-1L);
            root.setOpen(true);
            menuList.add(0, root);
        }

        saveMenuRsVo.setMenuList(menuList);
        return saveMenuRsVo;
    }

    public boolean saveMenu(SaveMenuReVo menuReVo) {
        Long menuId = menuReVo.getMenuId();
        if (null == menuId) {
            Menu menu = new Menu();
            menu.setName(menuReVo.getName());

            menu.setParentName("一级菜单");
            menu.setUrl(menuReVo.getUrl());
            menu.setPerms(menuReVo.getPerms());
            menu.setIcon(menuReVo.getIcon());
            menu.setType(menuReVo.getType());
            menu.setOrderNum(menuReVo.getOrderNum());
            Long parentId = menuReVo.getParentId();
            if (null == parentId) {
                menu.setParentId(0L);
            } else {
                menu.setParentId(menuReVo.getParentId());
            }
            String parentName = menuReVo.getParentName();
            if (StringUtils.isEmpty(parentName)) {
                menu.setParentName("一级菜单");
            } else {
                menu.setParentName(parentName);
            }
            Long createMenuId = (Long) menuDao.save(menu);
            return (null == createMenuId) ? false : true;
        } else {

        }
        return true;
    }

    public SaveMenuRsVo findEditPageData(Serializable menuId) {
        Menu menu = menuDao.findOne(Long.valueOf((String) menuId));
        String parentName = "";
        if (!(null == menu)) {
            Menu parentMenu = menuDao.findOne(menu.getParentId());
            if (!(null == parentMenu)) {
                parentName = parentMenu.getName();
                LOG.info("Parent menu, menu id: {}, name: {}", parentMenu.getMenuId(), parentName);
            }
        }
        menu.setParentName(parentName);
        //加入 菜单
        SaveMenuRsVo noButtonMenu = findNoButtonMenuList();
        return new SaveMenuRsVo(menu, noButtonMenu.getMenuList());
    }

}
