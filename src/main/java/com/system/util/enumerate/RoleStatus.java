package com.system.util.enumerate;

/**
 * 角色状态枚举
 */
public enum RoleStatus {

    /**
     * 开启状态
     */
    OPEN(1),
    /**
     * 关闭状态
     */
    CLOSE(0);

    private Integer status;

    private RoleStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

}
