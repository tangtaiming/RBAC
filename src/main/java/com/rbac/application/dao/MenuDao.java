package com.rbac.application.dao;

import com.rbac.application.orm.Menu;
import com.system.core.dao.BaseDao;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuDao extends BaseDao<Menu> {

    public MenuDao() {
        super(Menu.class);
    }

    public MenuDao(Class classes) {
        super(classes);
    }

}
