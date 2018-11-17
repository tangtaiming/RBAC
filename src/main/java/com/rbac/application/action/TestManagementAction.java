package com.rbac.application.action;

import com.rbac.application.action.core.MainAction;
import com.rbac.application.action.core.RbacAction;
import com.rbac.application.orm.User;
import com.rbac.application.service.UserService;
import com.system.util.base.AppPathUtils;
import com.system.util.base.DumperUtils;

import java.util.ArrayList;
import java.util.List;

public class TestManagementAction extends MainAction {

    private UserService userService;

    public String execute() {
//        userService = new UserService();
//        try {
//            _execute();
//            setDataList(userService.getDataList());
//            setPage(userService.getPage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return SUCCESS;
    }

}
