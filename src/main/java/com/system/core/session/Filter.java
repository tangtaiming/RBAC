package com.system.core.session;

import java.util.Map;

public class Filter {

    /**
     * 查询条件
     */
    private Map<String, Object> condition;

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
