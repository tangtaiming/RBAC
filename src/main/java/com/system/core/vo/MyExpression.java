package com.system.core.vo;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.util.Collection;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/10/28 0028
 **/
public class MyExpression implements Expression {

    @Override
    public Predicate isNull() {
        return null;
    }

    @Override
    public Predicate isNotNull() {
        return null;
    }

    @Override
    public Predicate in(Object... objects) {
        return null;
    }

    @Override
    public Expression as(Class aClass) {
        return null;
    }

    @Override
    public Predicate in(Expression expression) {
        return null;
    }

    @Override
    public Predicate in(Collection collection) {
        return null;
    }

    @Override
    public Predicate in(Expression[] expressions) {
        return null;
    }

    @Override
    public Selection alias(String s) {
        return null;
    }

    @Override
    public boolean isCompoundSelection() {
        return false;
    }

    @Override
    public List<Selection<?>> getCompoundSelectionItems() {
        return null;
    }

    @Override
    public Class getJavaType() {
        return null;
    }

    @Override
    public String getAlias() {
        return null;
    }

}
