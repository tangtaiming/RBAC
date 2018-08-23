package com.rbac.application.orm;

import com.rbac.application.orm.jstree.JstreeState;

import java.util.List;

/**
 * 菜单树
 * @auther ttm
 * @date 2018/8/23
 */
public class Jstree {

    private String id;

    private String text;

    private JstreeState state;

    private String icon;

    private List<Jstree> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JstreeState getState() {
        return state;
    }

    public void setState(JstreeState state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Jstree> getChildren() {
        return children;
    }

    public void setChildren(List<Jstree> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
