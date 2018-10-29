package com.system.core.domain;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class Sort {

    /**
     * 排序字段
     */
    private String propertyName;

    /**
     * 排序方式
     */
    private String sort;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
