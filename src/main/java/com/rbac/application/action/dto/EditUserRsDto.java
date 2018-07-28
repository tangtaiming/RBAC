package com.rbac.application.action.dto;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28
 */
public class EditUserRsDto {

    /**
     * id 查询用户
     */
    private UserDto user;

    /**
     * 所有角色
     */
    private List<RoleDto> allRoleDto;

    /**
     * 选中的角色
     */
    private List<Integer> chosenRole;

    public EditUserRsDto(UserDto user, List<RoleDto> allRoleDto, List<Integer> chosenRole) {
        this.user = user;
        this.allRoleDto = allRoleDto;
        this.chosenRole = chosenRole;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<RoleDto> getAllRoleDto() {
        return allRoleDto;
    }

    public void setAllRoleDto(List<RoleDto> allRoleDto) {
        this.allRoleDto = allRoleDto;
    }

    public List<Integer> getChosenRole() {
        return chosenRole;
    }

    public void setChosenRole(List<Integer> chosenRole) {
        this.chosenRole = chosenRole;
    }
}
