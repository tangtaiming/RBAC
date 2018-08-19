package com.rbac.application.action.vo;

import com.system.util.base.JsonUtils;

/**
 * @auther ttm
 * @date 2018/8/19 0019
 **/
public class ResponseVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResponseVo() {}

    public ResponseVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
