package com.system.core.page.select;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否是管理员
 * -1 代表不是超级管理员 999是管理员
 * @auther ttm
 * @date 2018/11/5 0005
 **/
public class YesNoAdmin {

    public Map<String, Object> getOption() {
        Map<String, Object> option = new HashMap<>();
        option.put("", "");
        option.put("-1", "否");
        option.put("999", "是");
        return option;
    }

}
