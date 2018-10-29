package com.system.core.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class SimpleOrderable<T> implements Orderable<T> {

    private Sort sort;

    public SimpleOrderable(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Order toOrder(Root<T> root, CriteriaBuilder criteriaBuilder) {
        Order order = toJpaOrder(root, criteriaBuilder, sort);
        return order;
    }

    private Order toJpaOrder(Root<T> root, CriteriaBuilder criteriaBuilder, Sort sort) {
        Order order = null;
        String sortName = sort.getSort();
        String propertyName = sort.getPropertyName();
        if (sortName.equals("asc")) {
            order = criteriaBuilder.asc(root.get(propertyName));
        } else {
            order = criteriaBuilder.desc(root.get(propertyName));
        }

        return order;
    }

}
