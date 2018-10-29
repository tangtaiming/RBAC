package com.rbac.application.action;

import com.rbac.application.action.core.MainAction;
import com.rbac.application.action.core.RbacAction;
import com.rbac.application.orm.User;
import com.rbac.application.service.UserService;
import com.system.util.base.AppPathUtils;
import com.system.util.base.JsonUtils;

import java.util.List;

public class TestManagementAction extends MainAction {

    private UserService userService = new UserService();

    public String page() {
        try {
            _execute();
            List<User> userList = userService.findUserList();
            setCollection(userList);
            calculationPage(userService.findUserAllListCount());

            System.out.println("Show: " + JsonUtils.toJson(getMain()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
