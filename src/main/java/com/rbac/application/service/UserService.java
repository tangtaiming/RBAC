package com.rbac.application.service;

import com.rbac.application.orm.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class UserService {

    private List<User> userList;

    public UserService() {
        initUsers();
    }

    private void initUsers() {
        userList = new ArrayList<>();
        User xiaoming = new User(1, "xiaoming", "xiaoming@qq.com");
        userList.add(xiaoming);
        User xiaohong = new User(2, "xiaohong", "xiaohong@qq.com");
        userList.add(xiaohong);
    }

    public User findUserByName(String name) {
        User findUser = null;
        for (User user : userList) {
            if (name.equals(user.getName())) {
                findUser = user;
                break;
            }
        }

        return findUser;
    }

}
