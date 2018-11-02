package com.system.core.page.select;

import java.util.HashMap;
import java.util.Map;

public class YesNo {

    public Map<String, String> getOption() {
        Map<String, String> option = new HashMap<>();
        option.put("", null);
        option.put("0", "否");
        option.put("1", "是");
        return option;
    }

}
