package com.rbac.application.action.dto;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class SaveAccessRsDto {

    private Integer id;

    private String title;

    private String urls;

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

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "SaveAccessRsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urls='" + urls + '\'' +
                '}';
    }
}
