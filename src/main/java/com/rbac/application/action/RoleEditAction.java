package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.EditRoleRsVo;
import com.rbac.application.action.vo.SaveRoleReVo;
import com.rbac.application.orm.Role;
import com.rbac.application.service.RoleService;
import com.system.util.base.ResultUtils;
import org.apache.commons.lang.StringUtils;

public class RoleEditAction extends EditAction<EditRoleRsVo> {

    private EditRoleRsVo editRoleRsVo;

    private SaveRoleReVo saveRoleReVo;

    private RoleService roleService = new RoleService();

    private static final String ERROR_KEY = "error";

    /**
     * 创建角色
     * @return
     */
    public String createRole() {
        try {
            _execute();
            setEntity(roleService.createRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 验证数据
     */
    public void validateSaveRole() {
        if (StringUtils.isEmpty(saveRoleReVo.getName())) {
            addFieldError(ERROR_KEY, ResultUtils.fail("角色名称不能为空"));
            return;
        }

        Role findRole = roleService.findRoleByName(saveRoleReVo.getName());
        if (null != findRole) {
            addFieldError(ERROR_KEY, ResultUtils.fail("角色名称已经存在, 保存失败"));
            return;
        }
    }

    public String saveRole() {
        LOG.info("Save role show: " + saveRoleReVo.toString());
        roleService.saveRole(saveRoleReVo);
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
        return SUCCESS;
    }

    public String deleteRole() {
        try {
            roleService.deleteRole(getId());
            setResult(ResultUtils.success());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public SaveRoleReVo getSaveRoleReVo() {
        return saveRoleReVo;
    }

    public void setSaveRoleReVo(SaveRoleReVo saveRoleReVo) {
        this.saveRoleReVo = saveRoleReVo;
    }

    public EditRoleRsVo getEditRoleRsVo() {
        return editRoleRsVo;
    }

    public void setEditRoleRsVo(EditRoleRsVo editRoleRsVo) {
        this.editRoleRsVo = editRoleRsVo;
    }
}
