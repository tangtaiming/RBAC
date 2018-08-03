package com.rbac.application.action.orm;

import com.rbac.application.orm.Access;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class AccessOrm {

    private Integer id;

    private String title;

    public AccessOrm(Access access) {
        id = access.getId();
        title = access.getTitle();
    }

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
