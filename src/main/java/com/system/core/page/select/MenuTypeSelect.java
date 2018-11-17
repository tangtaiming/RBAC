package com.system.core.page.select;

import com.system.util.base.LocalUtils;
import com.system.util.enumerate.MenuType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuTypeSelect {

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
