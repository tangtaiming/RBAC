package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import com.rbac.application.action.vo.DeleteMenuRsVo;
import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.action.vo.ValidateSaveMenuRsVo;
import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuManagementAction extends RbacAction {

    private MenuService menuService = new MenuService();

    private SaveMenuReVo saveMenuReVo;

    private SaveMenuRsVo saveMenuRsVo;

    private String result;

    /**
     * 菜单管理
     * @return
     */
    public String menuManagement() {
        _execute();
        List<Menu> menuList = menuService.findMenuAllList();
        Integer count = menuService.findMenuAllListCount();
        setDataList(menuList);
        setTotalRows(count);
        return SUCCESS;
    }

    /**
     * 创建菜单
     * @return
     */
    public String createMenu() {
        _execute();
        saveMenuRsVo = menuService.findNoButtonMenuList();
        return SUCCESS;
    }

    public String editMenu() {
        _execute();
        saveMenuRsVo = menuService.findEditPageData(getId());
        return SUCCESS;
    }

    /**
     * 校验菜单
     */
    public void validateSaveMenu() {
        //菜单名称 + 菜单类型确定唯一
        ValidateSaveMenuRsVo validateTypeAndNameFalg = menuService.validateSaveMenuData(saveMenuReVo);
        if (!validateTypeAndNameFalg.getCode().equals(200)) {
            addFieldError(ERROR, validateTypeAndNameFalg.getMessage());
            return;
        }
    }

    /**
     * 保存菜单
     * @return
     */
    public String saveMenu() {
        LOG.info(saveMenuReVo.toString());
        menuService.saveMenu(saveMenuReVo);
        setResult("success");
        return SUCCESS;
    }

    /**
     * 删除菜单
     * @return
     */
    public String deleteMenu() {
        try {
            String result = menuService.deleteMenu(getId());
            setResult(result);
            return SUCCESS;
        } catch (Exception e) {
            LOG.error("Delete error {}", e);
            addFieldError("error", "删除菜单异常！");
            return ERROR;
        }
    }

    public SaveMenuReVo getSaveMenuReVo() {
        return saveMenuReVo;
    }

    public void setSaveMenuReVo(SaveMenuReVo saveMenuReVo) {
        this.saveMenuReVo = saveMenuReVo;
    }

    public SaveMenuRsVo getSaveMenuRsVo() {
        return saveMenuRsVo;
    }

    public void setSaveMenuRsVo(SaveMenuRsVo saveMenuRsVo) {
        this.saveMenuRsVo = saveMenuRsVo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
