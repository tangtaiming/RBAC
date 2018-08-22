package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.dto.SaveAccessRsDto;
import com.rbac.application.orm.Access;
import com.rbac.application.service.AccessService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class AccessManagementAction extends RbacAction {

    private AccessService accessService = new AccessService();

    private List<Access> accessList;

    private SaveAccessRsDto accessRsDto;

    private String result;

    private Integer id;

    private static final String ERROR_KEY = "error";

    public String accessManagement() {
        _execute();
        List<Access> accessList = accessService.findAccessAllList();
        setAccessList(accessList);
        int count = accessService.findAccessAllListCount();
        setTotalRows(count);
        return SUCCESS;
    }

    public String createAccess() {
        _execute();
        return SUCCESS;
    }

    public void validateSaveAccess() {
        Integer accessId = accessRsDto.getId();
        if (StringUtils.isEmpty(accessRsDto.getTitle())) {
            addFieldError(ERROR_KEY, "标题不能为空!");
            return;
        }
        if (StringUtils.isEmpty(accessRsDto.getUrls())) {
            addFieldError(ERROR_KEY, "权限链接不能为空!");
            return;
        }

        if (null == accessId) {
            Access access = accessService.findAccessByTitle(accessRsDto.getTitle());
            if (null != access) {
                addFieldError(ERROR_KEY, accessRsDto.getTitle() + " 标题已经存在,请进行修改");
                return;
            }
        }
    }

    public String saveAccess() {
        accessService.saveAccess(accessRsDto);
        setResult("success");
        return SUCCESS;
    }

    public String editAccess() {
        _execute();
        SaveAccessRsDto findAccessRsDto = accessService.findAccessRsDtoOne(id);
        if (null == findAccessRsDto) {
            addFieldError(ERROR_KEY, "编辑异常, 查询权限为空");
            return INPUT;
        }

        setAccessRsDto(findAccessRsDto);
        return SUCCESS;
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    public SaveAccessRsDto getAccessRsDto() {
        return accessRsDto;
    }

    public void setAccessRsDto(SaveAccessRsDto accessRsDto) {
        this.accessRsDto = accessRsDto;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
