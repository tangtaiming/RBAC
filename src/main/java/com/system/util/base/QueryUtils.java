package com.system.util.base;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class QueryUtils {

    private List<Order> orders;

    public void addOrder(String propertyName, String order, Root from, CriteriaBuilder criteriaBuilder) {
        if (order == null || propertyName == null)
            return;

        if (this.orders == null)
            this.orders = new ArrayList();

        if (order.equalsIgnoreCase("asc")) {
            this.orders.add(criteriaBuilder.asc(from.get(propertyName)));
        } else if (order.equalsIgnoreCase("desc")) {
            this.orders.add(criteriaBuilder.desc(from.get(propertyName)));
        }
    }

}
