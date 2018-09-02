package com.rbac.application.action.vo;

/**
 * @auther ttm
 * @date 2018/9/2 0002
 **/
public class ValidateSaveMenuRsVo {

    private Integer code;

    private String message;

    public ValidateSaveMenuRsVo() {
        this.code = 200;
        this.message = "success";
    }

    private ValidateSaveMenuRsVo changeMessage(String message) {
        this.message = message;
        return this;
    }

    public ValidateSaveMenuRsVo fail(String message) {
        this.code = 400;
        return changeMessage(message);
    }

    public ValidateSaveMenuRsVo error(String message) {
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
