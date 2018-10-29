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

    private UserService userService = new UserService();

    public String execute() {
        List<User> userList = new ArrayList<>();
        try {
            _execute();
            userList = userService.findUserListTest();
            Integer totalCount = userService.findUserAllListCount();
            buildPageData(userList, totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DumperUtils.dump(userList);
        return SUCCESS;
    }

}
