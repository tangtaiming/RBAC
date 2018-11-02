package com.rbac.application.action.core;

/**
 * @auther ttm
 * @date 2018/11/3 0003
 **/
public class EditAction<E> extends RbacAction<E> {

    private E entity;

    public String execute() {
        _execute();
        return SUCCESS;
    }

}
