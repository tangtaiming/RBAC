package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.EditUserRsVo;
import com.rbac.application.action.vo.SaveUserReVo;
import com.rbac.application.action.vo.UserVo;
import com.rbac.application.orm.User;
import com.rbac.application.service.RoleService;
import com.rbac.application.service.UserService;
import com.system.util.base.ResultUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @auther ttm
 * @date 2018/11/6 0006
 **/
public class UserEditAction extends EditAction<UserVo> {

    private SaveUserReVo userReVo;

    private UserService userService = new UserService();

    private RoleService roleService = new RoleService();

    private static final String ERROR_KEY = "error";

    private EditUserRsVo editUserRsVo;

    private UserVo userVo;

    public String deleteUser() {
        userService.deleteUser(getId());
        setResult(ResultUtils.success());
        return SUCCESS;
    }

    public String editUser() {
        try {
            _execute();
            userVo = userService.findUserVoById(getId());
            setEntity(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String createUser() {
        try {
            _execute();
            setEntity(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validateSaveUser() {
        if (StringUtils.isEmpty(userReVo.getName())) {
            addFieldError(ERROR_KEY, ResultUtils.fail("保存用户名称不能为空"));
            return;
        }
        if (StringUtils.isEmpty(userReVo.getEmail())) {
            addFieldError(ERROR_KEY, ResultUtils.fail("保存用户邮箱不能为空"));
            return;
        }

        if (null == userReVo.getId()) {
            User findUser = userService.findUserByName(userReVo.getName());
            if (null != findUser) {
                addFieldError(ERROR_KEY, ResultUtils.fail("保存用户名称已经存在，请进行数据修改!"));
                return;
            }
        }
    }

    public String saveUser() {
        LOG.info(userReVo.toString());
        userService.saveUser(userReVo);
        setResult(ResultUtils.success());
        return SUCCESS;
    }

    public SaveUserReVo getUserReVo() {
        return userReVo;
    }

    public void setUserReVo(SaveUserReVo userReVo) {
        this.userReVo = userReVo;
    }

    public EditUserRsVo getEditUserRsVo() {
        return editUserRsVo;
    }

    public void setEditUserRsVo(EditUserRsVo editUserRsVo) {
        this.editUserRsVo = editUserRsVo;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
