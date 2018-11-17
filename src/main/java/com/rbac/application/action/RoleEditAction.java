package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.EditRoleRsVo;
import com.rbac.application.orm.Role;
import com.rbac.application.service.RoleService;
import com.system.util.base.ResultUtils;
import org.apache.commons.lang.StringUtils;

public class RoleEditAction extends EditAction {

    private EditRoleRsVo editRoleRsVo;

    private RoleService roleService = new RoleService();

    private static final String ERROR_KEY = "error";

    /**
     * 创建角色
     * @return
     */
    public String createRole() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 验证数据
     */
    public void validateSaveRole() {
        if (StringUtils.isEmpty(editRoleRsVo.getName())) {
            addFieldError(ERROR_KEY, "角色名称不能为空");
            return;
        }

        Role findRole = roleService.findRoleByName(editRoleRsVo.getName());
        if (null != findRole) {
            addFieldError(ERROR_KEY, "角色名称已经存在, 保存失败");
            return;
        }
    }

    public String saveRole() {
        LOG.info("Save role show: " + editRoleRsVo.toString());
        roleService.saveRole(editRoleRsVo);
        setResult(ResultUtils.success());
        return SUCCESS;
    }

    /**
     * 编辑角色
     * @return
     */
    public String editRole() {
        try {
            _execute();
            setEntity(roleService.findEditRoleRsVoOne(getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Role role = roleService.findRoleOne(getId());
//        if (null == role) {
//            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
//            return INPUT;
//        }
//
//        editRoleRsVo = new EditRoleRsVo(role);
        return SUCCESS;
    }



    public EditRoleRsVo getEditRoleRsVo() {
        return editRoleRsVo;
    }

    public void setEditRoleRsVo(EditRoleRsVo editRoleRsVo) {
        this.editRoleRsVo = editRoleRsVo;
    }
}
