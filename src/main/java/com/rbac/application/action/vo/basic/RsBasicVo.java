package com.rbac.application.action.vo.basic;

import com.rbac.application.action.vo.ValidateSaveMenuRsVo;

/**
 * @auther ttm
 * @date 2018/9/3 0003
 **/
public abstract class RsBasicVo {

    private Integer code;

    private String message;

    public RsBasicVo() {
        this.code = 200;
        this.message = "success";
    }

    private RsBasicVo changeMessage(String message) {
        this.message = message;
        return this;
    }

    public RsBasicVo fail(String message) {
        this.code = 400;
        return changeMessage(message);
    }

    public RsBasicVo error(String message) {
        this.code = 500;
        return changeMessage(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
