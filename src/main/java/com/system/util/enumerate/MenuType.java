package com.system.util.enumerate;

/**
 * 菜单类型
 */
public enum MenuType {

    /**
     * 0：目录
     */
    DIRECTORY(0),
    /**
     * 1：菜单
     */
    MENU(1),
    /**
     * 2：按钮
     */
    BUTTON(2);

    private Integer type;

    MenuType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
