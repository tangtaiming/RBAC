package com.rbac.application.action;

import com.rbac.application.action.core.EditAction;
import com.rbac.application.action.vo.SaveMenuReVo;
import com.rbac.application.action.vo.SaveMenuRsVo;
import com.rbac.application.action.vo.ValidateSaveMenuRsVo;
import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;

/**
 * 菜单编辑请求
 * @auther ttm
 * @date 2018/11/18 0018
 **/
public class MenuEditAction extends EditAction<SaveMenuRsVo> {

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
//            setEntity(menuService.findOneMenuList());
        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
