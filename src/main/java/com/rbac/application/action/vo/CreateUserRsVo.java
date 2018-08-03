package com.rbac.application.action.vo;

import com.rbac.application.action.orm.BaseUserOrm;
import com.rbac.application.orm.Role;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3 0003
 **/
public class CreateUserRsVo extends BaseUserOrm {

    public CreateUserRsVo(List<Role> roleList) {
        putRoleOrmList(roleList);
    }

}
