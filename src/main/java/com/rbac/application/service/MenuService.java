package com.rbac.application.service;

import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.dao.MenuDao;
import com.rbac.application.orm.Menu;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuService {

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
            menuList.add(root);
        }

        saveMenuRsVo.setMenuList(menuList);
        return saveMenuRsVo;
    }

    public boolean saveMenu(SaveMenuReVo menuReVo) {
        Long menuId = menuReVo.getMenuId();
        if (null == menuId) {
            Long parentId = Long.valueOf(Optional.ofNullable(menuReVo.getParentId()).orElse("0"));
            Integer type = Integer.valueOf(menuReVo.getType());
            Integer orderNum = Integer.valueOf(Optional.ofNullable(menuReVo.getOrderNum()).orElse("0"));
            Menu menu = new Menu();
            menu.setName(menuReVo.getName());
            menu.setParentId(parentId);
            menu.setParentName("一级菜单");
            menu.setUrl(menuReVo.getUrl());
            menu.setPerms(menuReVo.getPerms());
            menu.setIcon(menuReVo.getIcon());
            menu.setType(type);
            menu.setOrderNum(orderNum);
            Integer createMenuId = menuDao.save(menu);
            return (null == createMenuId) ? false : true;
        } else {

        }
        return true;
    }

}
