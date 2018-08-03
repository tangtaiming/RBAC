package com.rbac.application.action.vo;

import com.rbac.application.action.orm.AccessOrm;
import com.rbac.application.orm.Access;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.RoleAccess;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class SiteAccessRsVo {

    private Integer id;

    private String name;

    /**
     * 所有权限
     */
    private List<AccessOrm> accessList;

    /**
     * 选中权限
     */
    private List<Integer> chosenAccess;

    public SiteAccessRsVo(Role role, List<Access> accessList, List<RoleAccess> chosenAccess) {
        id = role.getId();
        name = role.getName();
        putAccessOrm(accessList);
        putChosenAccess(chosenAccess);
    }

    private void putAccessOrm(List<Access> accesseList) {
        accessList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accesseList)) {
            for (Access access : accesseList) {
                AccessOrm accessOrm = new AccessOrm(access);
                accessList.add(accessOrm);
            }
        }
    }

    private void putChosenAccess(List<RoleAccess> roleAccessList) {
        chosenAccess = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleAccessList)) {
            for (RoleAccess roleAccess : roleAccessList) {
                chosenAccess.add(roleAccess.getAccessId());
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccessOrm> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<AccessOrm> accessList) {
        this.accessList = accessList;
    }

    public List<Integer> getChosenAccess() {
        return chosenAccess;
    }

    public void setChosenAccess(List<Integer> chosenAccess) {
        this.chosenAccess = chosenAccess;
    }
}
