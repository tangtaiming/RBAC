package com.rbac.application.action.vo;

/**
 * @auther ttm
 * @date 2018/8/19 0019
 **/
public class LoginRsVo {

    /**
     * 用于显示登录名称
     */
    private String name;

    public LoginRsVo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
