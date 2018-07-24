package com.rbac.application.orm;

/**
 * 角色
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class Role {

    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    private String createDate;

    private String updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
