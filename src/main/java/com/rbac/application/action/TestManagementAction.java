package com.rbac.application.action;

import com.rbac.application.action.core.MainAction;
import com.rbac.application.action.core.RbacAction;
import com.system.util.base.AppPathUtils;

public class TestManagementAction extends MainAction {

    public String execute() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("app path: " + AppPathUtils.getAppPageXmlPath());
        return SUCCESS;
    }

}
