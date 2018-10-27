package com.rbac.applicatio;

import com.rbac.application.orm.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/9/13
 */
public class OptionalTest {

    @Test
    public void oTest() {
        User user = null;
        String name = Optional.ofNullable(user).orElse(new User()).getName();
        System.out.println("name:" + name);
    }

}
