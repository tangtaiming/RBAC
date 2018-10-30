package com.system.core.domain;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class SimpleOrderableBuilder<T> {

    private Sort sort;

    /**
     * 默认使用 ID 排序
     */
    public SimpleOrderableBuilder() {
        sort = new Sort();
        sort.setPropertyName("id");
        sort.setSort("asc");
    }

    /**
     * 设置排序方式
     * @param propertyName
     * @param sortName
     * @return
     */
    public SimpleOrderableBuilder setJpaOrder(String propertyName, String sortName) {
        sort = new Sort();
        sort.setPropertyName(propertyName);
        sort.setSort(sortName);
        return this;
    }

    public SimpleOrderableBuilder setJpaOrder(Sort sort) {
        this.sort = sort;
        return this;
    }

    /**
     * 升序
     * @param propertyName
     * @return
     */
    public SimpleOrderableBuilder asc(String propertyName) {
        return setJpaOrder(propertyName, "asc");
    }

    /**
     * 降序
     * @param propertyName
     * @return
     */
    public SimpleOrderableBuilder desc(String propertyName) {
        return setJpaOrder(propertyName, "desc");
    }

    /**
     * 获取排序
     * @return
     */
    public Orderable getOrderable() {
        SimpleOrderable simpleOrderable = new SimpleOrderable(sort);
        return  simpleOrderable;
    }


}
