package com.rbac.application.action.vo;

import java.io.Serializable;

/**
 * 菜单VO
 * @auther ttm
 * @date 2018/8/24
 */
public class SaveMenuReVo implements Serializable {

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 父菜单名称，一级菜单为0
     */
    private String parentName;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权
     */
    private String perms;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单类型
     */
    private Integer type;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SaveMenuReVo{" +
                "menuId=" + menuId +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", orderNum=" + orderNum +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                '}';
    }
}
