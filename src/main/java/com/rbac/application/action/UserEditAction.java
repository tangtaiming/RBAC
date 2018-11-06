package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.SaveUserReVo;
import com.rbac.application.orm.User;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import org.apache.commons.lang.StringUtils;

/**
 * @auther ttm
 * @date 2018/11/6 0006
 **/
public class UserEditAction extends EditAction {

    private SaveUserReVo userReVo;

    private UserService userService = new UserService();

    private RoleService roleService = new RoleService();

    private static final String ERROR_KEY = "error";

    public String createUser() {
        try {
            _execute();
            setEntity(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<Role> roleList = roleService.findRoleList();
//        createUserRsVo = new CreateUserRsVo(roleList);
        return SUCCESS;
    }

    public void validateSaveUser() {
        if (StringUtils.isEmpty(userReVo.getName())) {
            addFieldError(ERROR_KEY, "保存用户名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(userReVo.getEmail())) {
            addFieldError(ERROR_KEY, "保存用户邮箱不能为空");
            return;
        }

        if (null == userReVo.getId()) {
            User findUser = userService.findUserByName(userReVo.getName());
            if (null != findUser) {
                addFieldError(ERROR_KEY, "保存用户名称已经存在，请进行数据修改!");
                return;
            }
        }
    }

    public String saveUser() {
        LOG.info(userReVo.toString());
        userService.saveUser(userReVo);
        setResult("success");
        return SUCCESS;
    }

    public SaveUserReVo getUserReVo() {
        return userReVo;
    }

    public void setUserReVo(SaveUserReVo userReVo) {
        this.userReVo = userReVo;
    }
}
