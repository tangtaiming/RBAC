package com.rbac.application.orm;

import java.io.Serializable;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class Access implements Serializable {

    private Integer id;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 权限地址
     */
    private String url;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 菜单id
     */
    private Integer menuId;

    private String createDate;

    private String updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
