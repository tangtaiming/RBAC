package com.rbac.applicatio;

import com.system.util.base.LocalUtils;

import java.util.Locale;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class LocalTest {

//    @Test
    public void packageText() {
        LocalUtils local = new LocalUtils(Locale.SIMPLIFIED_CHINESE);
        System.out.println("--> : " + local.getText("basic.site"));
    }

}
