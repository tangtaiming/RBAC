package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.orm.User;
import com.system.util.base.DumperUtils;

/**
 * @auther ttm
 * @date 2018/11/3 0003
 **/
public class TestEditAction extends EditAction {

    public User userVo;

    public String execute() {
        try {
            _execute();
            setEntity(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String savePage2() {
        DumperUtils.dump(userVo);
        return SUCCESS;
    }

    public User getUserVo() {
        return userVo;
    }

    public void setUserVo(User userVo) {
        this.userVo = userVo;
    }
}
