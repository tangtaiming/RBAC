package com.rbac.application.action.vo;

import com.rbac.application.action.orm.BaseUserOrm;
import com.rbac.application.orm.Role;
import com.rbac.application.orm.User;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/3
 */
public class EditUserRsVo extends BaseUserOrm {

    private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    private List<Integer> chosenRole;

    public EditUserRsVo(User user, List<Role> ormList, List<Integer> chosenRole) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        this.chosenRole = chosenRole;
        putRoleOrmList(ormList);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getChosenRole() {
        return chosenRole;
    }

    public void setChosenRole(List<Integer> chosenRole) {
        this.chosenRole = chosenRole;
    }
}
