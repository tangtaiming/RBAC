package com.rbac.application.action.dto;

/**
 * @auther ttm
 * @date 2018/7/26 0026
 **/
public class RoleDto {

    private Integer id;

    /**
     * 角色名称
     */
    private String name;

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
}
