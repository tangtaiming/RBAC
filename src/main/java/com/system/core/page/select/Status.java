package com.system.core.page.select;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户状态
 * 1 代表对于账号开启使用状态， 0 表示账号冻结
 * @auther ttm
 * @date 2018/11/5 0005
 **/
public class Status {

    public Map<String, Object> getOption() {
        Map<String, Object> option = new HashMap<>();
        option.put("", "");
        option.put("1", "正常");
        option.put("0", "冻结");
        return option;
    }

}
