package com.rbac.application.action.vo;

/**
 * 菜单VO
 * @auther ttm
 * @date 2018/8/24
 */
public class SaveMenuReVo {

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
    private String parentId;

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
    private String orderNum;

    /**
     * 图标
     */
    private String icon;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
                ", orderNum='" + orderNum + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
