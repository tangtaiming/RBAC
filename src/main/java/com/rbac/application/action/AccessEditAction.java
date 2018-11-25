package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.CreateAccessRsVo;

/**
 * @auther ttm
 * @date 2018/11/25 0025
 **/
public class AccessEditAction extends EditAction {

    private CreateAccessRsVo accessRsVo;

    public String createAccess() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        accessRsVo = new CreateAccessRsVo();
        return SUCCESS;
    }

    public CreateAccessRsVo getAccessRsVo() {
        return accessRsVo;
    }

    public void setAccessRsVo(CreateAccessRsVo accessRsVo) {
        this.accessRsVo = accessRsVo;
    }

}
