package com.rbac.application.action.vo;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class SaveRoleReVo {

    private Integer id;

    private String name;

    /**
     * 保存菜单
     */
    private List<Long> menuIdList;

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

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    @Override
    public String toString() {
        return "SaveRoleReVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
