package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;

/**
 * @auther ttm
 * @date 2018/11/3 0003
 **/
public class TestEditAction extends EditAction {

    public String execute() {
        try {
            _execute();
            setEntity(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
