package com.rbac.application.action.vo;

import com.rbac.application.orm.Menu;
import com.system.util.base.JsonUtils;

import java.util.List;

/**
 * ztree 菜单
 * @auther ttm
 * @date 2018/8/24
 */
public class SaveMenuRsVo {

    private List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getMenuJson() {
        return JsonUtils.toJson(menuList);
    }
}
