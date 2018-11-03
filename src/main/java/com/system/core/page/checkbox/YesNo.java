package com.system.core.page.checkbox;

import com.system.core.page.Module;

import java.util.Map;

public class YesNo implements Module {

    @Override
    public Map<String, String> getOption() {
        com.system.core.page.select.YesNo yesNo = new com.system.core.page.select.YesNo();
        return yesNo.getOption();
    }
}
