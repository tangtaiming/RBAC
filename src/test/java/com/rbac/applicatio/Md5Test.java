package com.rbac.applicatio;

import com.system.util.base.MD5Utils;

/**
 * @auther ttm
 * @date 2018/8/19 0019
 **/
public class Md5Test {

    public static void main(String[] args) {
        System.out.println("Md5: " + MD5Utils.encoder("Taiming@123"));
    }

}
