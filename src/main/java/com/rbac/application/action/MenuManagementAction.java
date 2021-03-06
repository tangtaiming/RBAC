package com.rbac.application.action;

import com.rbac.application.action.core.MainAction;
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
public class MenuManagementAction extends MainAction {

    private MenuService menuService = new MenuService();

    private SaveMenuReVo saveMenuReVo;

    private SaveMenuRsVo saveMenuRsVo;

    private String result;

    private Menu menu;

    /**
     * 菜单管理
     * @return
     */
    public String menuManagement() {
        try {
            _execute();
            setDataList(menuService.getDataList());
            setPage(menuService.getPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<Menu> menuList = menuService.findMenuAllList();
//        Integer count = menuService.findMenuAllListCount();
//        setDataList(menuList);
//        setTotalRows(count);
        return SUCCESS;
    }

//    /**
//     * 创建菜单
//     * @return
//     */
//    public String createMenu() {
//        try {
//            _execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        saveMenuRsVo = menuService.findNoButtonMenuList();
//        return SUCCESS;
//    }

//    /**
//     * 校验菜单
//     */
//    public void validateSaveMenu() {
//        //菜单名称 + 菜单类型确定唯一
//        ValidateSaveMenuRsVo validateTypeAndNameFalg = menuService.validateSaveMenuData(saveMenuReVo);
//        if (!validateTypeAndNameFalg.getCode().equals(200)) {
//            addFieldError(ERROR, validateTypeAndNameFalg.getMessage());
//            return;
//        }
//    }
//
//    /**
//     * 保存菜单
//     * @return
//     */
//    public String saveMenu() {
//        LOG.info(saveMenuReVo.toString());
//        menuService.saveMenu(saveMenuReVo);
//        setResult("success");
//        return SUCCESS;
//    }

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
