package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuManagementAction extends RbacAction {

    private MenuService menuService = new MenuService();

    /**
     * 菜单管理
     * @return
     */
    public String menuManagement() {
        _execute();
        List<Menu> menuList = menuService.findMenuAllList();
        Integer count = menuService.findMenuAllListCount();
        setDataList(menuList);
        setTotalRows(count);
        return SUCCESS;
    }

    /**
     * 创建菜单
     * @return
     */
    public String createMenu() {
        _execute();
        return SUCCESS;
    }

}
