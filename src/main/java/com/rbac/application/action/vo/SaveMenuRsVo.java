package com.rbac.application.action.vo;

import com.rbac.application.orm.Menu;
import com.system.util.base.JsonUtils;
import com.system.util.enumerate.MenuType;

import java.util.List;

/**
 * ztree 菜单
 * @auther ttm
 * @date 2018/8/24
 */
public class SaveMenuRsVo {

    /**
     * 菜单ID
     */
    private Long id;

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

    /**
     * 是否是父类别
     */
    private String isParent;

    /**
     * 不显示某个节点的 checkbox / radio
     */
    private boolean nocheck;

    private List<SaveMenuRsVo> menuList;

//    public SaveMenuRsVo(Menu menu, List<Menu> noButtonMenu) {
//        this(menu);
//        menuList = noButtonMenu;
//    }

//    public SaveMenuRsVo(Menu menu) {
//        this.id = menu.getId();
//        this.name = menu.getName();
//        this.parentId = menu.getParentId();
//        this.parentName = menu.getParentName();
//        this.url = menu.getUrl();
//        this.icon = menu.getIcon();
//        this.perms = menu.getPerms();
//        this.type = menu.getType();
//        this.orderNum = menu.getOrderNum();
//        if (!(this.type == MenuType.BUTTON.getType())) {
//            this.isParent = "true";
//        } else {
//            this.isParent = "false";
//        }
//    }

    public String getMenuJson() {
        return JsonUtils.toJson(menuList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public List<SaveMenuRsVo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SaveMenuRsVo> menuList) {
        this.menuList = menuList;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
}
