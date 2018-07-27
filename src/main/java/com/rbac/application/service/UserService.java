package com.rbac.application.service;

import com.rbac.application.action.dto.RoleDto;
import com.rbac.application.action.dto.UserDto;
import com.rbac.application.action.dto.UserRsDto;
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

    public User findStaticUserOne(Integer uid) {
        User userOne = null;
        for (User user : staticUserList) {
            if (user.getId().equals(uid)) {
                userOne = user;
                break;
            }
        }

        return userOne;
    }

    public UserDto findStaticUserDtoOne(Integer uid) {
        User userOne = findStaticUserOne(uid);
        if (null != userOne) {
            Integer id = userOne.getId();
            String email = userOne.getEmail();
            String name = userOne.getName();
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setEmail(email);
            userDto.setName(name);
            return userDto;
        }

        return null;
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

    public User findStaticUserOne(Integer id) {
        User findUser = null;
        for (User user : staticUserList) {
            if (user.getId().equals(id)) {
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

    public boolean saveUser(UserRsDto user) {
        Integer userId = user.getId();
        String format = "yyyy-MM-dd hh:mm:ss";
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        //-1 代表不是超级管理员
        Integer admin = -1;
        //1 代表对于账号开启使用状态， 0 表示账号冻结
        Integer status = 1;
        if (null != userId) {
            User findUser = findStaticUserOne(userId);
            if (null != findUser) {
                Integer index = staticUserList.indexOf(findUser);
                findUser.setUpdateDate(currentTime);
                findUser.setName(user.getName());
                findUser.setEmail(user.getEmail());
                staticUserList.set(index, findUser);
                return true;
            }
        } else {
            User createUser = new User();
            String email = user.getEmail();
            String name = user.getName();
            List<Integer> roles = user.getRoles();
            createUser.setId(staticUserList.size() + 1);
            createUser.setEmail(email);
            createUser.setName(name);
            createUser.setAdmin(admin);
            createUser.setStatus(status);
            createUser.setCreateDate(currentTime);
            createUser.setUpdateDate(currentTime);
            staticUserList.add(createUser);
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
