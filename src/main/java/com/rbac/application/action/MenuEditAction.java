package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.action.vo.ValidateSaveMenuRsVo;
import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;
import com.system.util.base.ResultUtils;

/**
 * 菜单编辑请求
 * @auther ttm
 * @date 2018/11/18 0018
 **/
public class MenuEditAction extends EditAction<SaveMenuReVo> {

    private MenuService menuService = new MenuService();

    private SaveMenuReVo saveMenuReVo;

    private SaveMenuRsVo saveMenuRsVo;

    /**
     * 根据id查询对应对应子类别
     * @return
     */
    public String fetchMenuByParentId() {
        try {
            setResult(menuService.fetchMenuByParentId(getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 创建菜单
     * @return
     */
    public String createMenu() {
        try {
            _execute();
            setEntity(menuService.createMenu());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String editMenu() {
        try {
            _execute();
            setEntity(menuService.editMenu(getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        menuService.saveMenu(saveMenuReVo);
        setResult(ResultUtils.success());
        return SUCCESS;
    }

    /**
     * 删除菜单
     * @return
     */
    public String deleteMenu() {
        try {
            String result = menuService.deleteMenu(getId());
            setResult(ResultUtils.success());
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
}
