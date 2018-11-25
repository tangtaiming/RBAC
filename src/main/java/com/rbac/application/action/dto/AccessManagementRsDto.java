package com.rbac.application.action.dto;

/**
 * 管理页面页面与后端交互DTO
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class AccessManagementRsDto {

    private Integer id;

    private String title;

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

}
