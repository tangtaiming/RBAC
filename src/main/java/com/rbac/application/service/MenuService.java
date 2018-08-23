package com.rbac.application.service;

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

}
