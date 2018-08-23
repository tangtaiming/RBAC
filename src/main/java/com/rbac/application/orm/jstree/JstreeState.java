package com.rbac.application.orm.jstree;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class JstreeState {

    public boolean selected;

    private boolean disabled;

    private boolean opened;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
