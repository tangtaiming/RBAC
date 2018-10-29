package com.system.core.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * 排序
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public interface Orderable<T> {

    public Order toOrder(Root<T> root, CriteriaBuilder criteriaBuilder);

}
