package com.system.core.page.radio;

import com.system.util.base.LocalUtils;
import com.system.util.enumerate.MenuType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/11/18 0018
 **/
public class MenuTypeRadio {

    public Map<String, String> getOption() {
        Map<String, String> option = new HashMap<>();
        Locale locale = new Locale("zh", "CN");
        LocalUtils local = new LocalUtils(locale);
        for (MenuType menuType : MenuType.values()) {
            option.put(menuType.getType().toString(), local.getText(menuType.name()));
        }

        return option;
    }

}
