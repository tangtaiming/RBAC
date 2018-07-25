package com.rbac.application.service;

import com.rbac.application.orm.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class UserService {

    private List<User> userList;

    /**
     * 模拟保存操作静态实体集合
     */
    private static List<User> staticUserList = new ArrayList<>();

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

    public User findUserOne(Integer uid) {
        User userOne = null;
        for (User user : userList) {
            if (uid.equals(user.getId())) {
                userOne = user;
                break;
            }
        }

        return userOne;
    }

    public User findStaticUserByName(String name) {
        User findUser = null;
        for (User user : staticUserList) {
            if (name.equals(user.getName())) {
                findUser = user;
                break;
            }
        }

        return findUser;
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

    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> findUserList() {
        return staticUserList;
    }

    public boolean saveUser(User user) {
        Integer userId = user.getId();
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        //-1 代表不是超级管理员
        Integer admin = -1;
        //1 代表对于账号开启使用状态， 0 表示账号冻结
        Integer status = 1;
        if (null != userId) {
            User findUser = findUserOne(userId);
            if (null != findUser) {
                Integer index = staticUserList.indexOf(findUser);
                user.setUpdateDate(currentTime);
                user.setName(user.getName());
                user.setEmail(user.getEmail());
                staticUserList.set(index, findUser);
                return true;
            }
        } else {
            user.setId(staticUserList.size() + 1);
            user.setAdmin(admin);
            user.setStatus(status);
            user.setCreateDate(currentTime);
            user.setUpdateDate(currentTime);
            staticUserList.add(user);
            return true;
        }

        return false;
    }

    public static List<User> getStaticUserList() {
        return staticUserList;
    }

    public static void setStaticUserList(List<User> staticUserList) {
        UserService.staticUserList = staticUserList;
    }
}
