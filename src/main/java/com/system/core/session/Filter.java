package com.system.core.session;

import com.system.core.domain.Specification;

import java.util.Map;

public class Filter {

    /**
     * 查询条件
     */
    private Map<String, Object> condition;

    /**
     * mysql 数据库查询条件
     */
    private Specification specification;

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
}
