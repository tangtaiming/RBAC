package com.system.util.base;

import com.rbac.application.orm.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther ttm
 * @date 2018/11/6 0006
 **/
public class AutoTokenUtils {

    public static String createAutoToken(User user, HttpServletRequest request) {
        String encode = user.getId() + user.getName() + user.getPassword() + request.getHeader("user-agent");
        return MD5Utils.encoder(encode);
    }

}
