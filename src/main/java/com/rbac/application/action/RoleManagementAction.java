package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.dto.SaveSiteAccessRqDto;
import com.rbac.application.action.vo.*;
import com.rbac.application.orm.Access;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.RoleAccess;
import com.rbac.application.service.AccessService;
import com.rbac.application.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/25 0025
 **/
public class RoleManagementAction extends RbacAction {

    private static final Logger LOG = LoggerFactory.getLogger(RoleManagementAction.class);

    private RoleService roleService = new RoleService();

    private AccessService accessService = new AccessService();

    private List<Role> roleList;

    private List<Access> accessList;

    private String result;

    private static final String ERROR_KEY = "error";

    private SaveSiteAccessRqDto siteAccessRqDto;

    private List<RoleAccess> roleAccessList;

    private List<RoleManagementRsVo> roleRsVo;

    private EditRoleRsVo editRoleRsVo;

    private SaveRoleReVo saveRoleReVo;

    private SiteAccessRsVo siteAccessRsVo;

    private SaveSiteAccessReVo saveSiteAccessReVo;

    /**
     * 角色管理
     * @return
     */
    public String roleManagement() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        roleRsVo = roleService.findRoleManagementRsVo();
        int count = roleService.findRoleAllListCount();
//        setTotalRows(count);
        return SUCCESS;
    }

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
        if (StringUtils.isEmpty(saveRoleReVo.getName())) {
            addFieldError(ERROR_KEY, "角色名称不能为空");
            return;
        }

        Role findRole = roleService.findRoleByName(saveRoleReVo.getName());
        if (null != findRole) {
            addFieldError(ERROR_KEY, "角色名称已经存在, 保存失败");
            return;
        }
    }

    public String saveRole() {
        LOG.info("Save role show: " + saveRoleReVo.toString());
        roleService.saveRole(saveRoleReVo);
        setResult("success");
        return SUCCESS;
    }

    /**
     * 编辑角色
     * @return
     */
    public String editRole() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Role role = roleService.findRoleOne(getId());
        if (null == role) {
            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
            return INPUT;
        }

        editRoleRsVo = new EditRoleRsVo(role);
        return SUCCESS;
    }

    /**
     * 设置权限
     * @return
     */
    public String siteAccess() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Role role = roleService.findRoleOne(getId());
        if (null == role) {
            addFieldError(ERROR_KEY, "编辑异常, 查询角色为空");
            return INPUT;
        }

        //循环获取所有权限列表
        List<Access> accessList = accessService.findAccessAllList();
        //根据角色查询角色对应权限
        List<RoleAccess> roleAccessList = roleService.findRoleAccessByRoleId(role.getId());
        siteAccessRsVo = new SiteAccessRsVo(role, accessList, roleAccessList);
        return SUCCESS;
    }

    public String saveSiteAccess() {
        roleService.saveSiteAccess(saveSiteAccessReVo);
        setResult("success");
        return SUCCESS;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    public SaveSiteAccessRqDto getSiteAccessRqDto() {
        return siteAccessRqDto;
    }

    public void setSiteAccessRqDto(SaveSiteAccessRqDto siteAccessRqDto) {
        this.siteAccessRqDto = siteAccessRqDto;
    }

    public List<RoleAccess> getRoleAccessList() {
        return roleAccessList;
    }

    public void setRoleAccessList(List<RoleAccess> roleAccessList) {
        this.roleAccessList = roleAccessList;
    }

    public List<RoleManagementRsVo> getRoleRsVo() {
        return roleRsVo;
    }

    public void setRoleRsVo(List<RoleManagementRsVo> roleRsVo) {
        this.roleRsVo = roleRsVo;
    }

    public EditRoleRsVo getEditRoleRsVo() {
        return editRoleRsVo;
    }

    public void setEditRoleRsVo(EditRoleRsVo editRoleRsVo) {
        this.editRoleRsVo = editRoleRsVo;
    }

    public SaveRoleReVo getSaveRoleReVo() {
        return saveRoleReVo;
    }

    public void setSaveRoleReVo(SaveRoleReVo saveRoleReVo) {
        this.saveRoleReVo = saveRoleReVo;
    }

    public SiteAccessRsVo getSiteAccessRsVo() {
        return siteAccessRsVo;
    }

    public void setSiteAccessRsVo(SiteAccessRsVo siteAccessRsVo) {
        this.siteAccessRsVo = siteAccessRsVo;
    }

    public SaveSiteAccessReVo getSaveSiteAccessReVo() {
        return saveSiteAccessReVo;
    }

    public void setSaveSiteAccessReVo(SaveSiteAccessReVo saveSiteAccessReVo) {
        this.saveSiteAccessReVo = saveSiteAccessReVo;
    }
}
