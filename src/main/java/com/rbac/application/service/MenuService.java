package com.rbac.application.service;

import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.dao.MenuDao;
import com.rbac.application.orm.Menu;

import java.util.List;

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
        //默认添加顶级类目
        Menu root = new Menu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        saveMenuRsVo.setMenuList(menuList);
        return saveMenuRsVo;
    }

}
