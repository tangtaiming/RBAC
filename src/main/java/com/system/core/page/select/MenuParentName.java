package com.system.core.page.select;

import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuParentName {

    private MenuService menuService = new MenuService();

    public Map<String, String> getOption() {
        Map<String, String> option = new HashMap<>();
        option.put("", "");
        option.put(MenuService.ROOTID.toString(), MenuService.ROOTNAME);

        Map<String, Object> query = new HashMap<>();
        List<Menu> menuList = menuService.findMenuList(query);
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                option.put(menu.getId().toString(), menu.getName());
            }
        }
        return option;
    }

}
