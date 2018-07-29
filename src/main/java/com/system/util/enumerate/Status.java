package com.system.util.enumerate;

/**
 * @auther ttm
 * @date 2018/7/29 0029
 **/
public enum Status {

    /**
     * 开启状态
     */
    OPEN(1),
    /**
     * 关闭状态
     */
    CLOSE(0);

    private Integer status;

    private Status(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

}
