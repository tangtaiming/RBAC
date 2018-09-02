package com.rbac.application.action.vo;

import com.rbac.application.action.vo.basic.RsBasicVo;
import com.system.util.base.JsonUtils;

/**
 * @auther ttm
 * @date 2018/9/3 0003
 **/
public class DeleteMenuRsVo extends RsBasicVo {

    /**
     * 返回json字符串数据
     * @return
     */
    public String success() {
        return JsonUtils.toJson(this);
    }

    public String menuFailMenu(String message) {
        return JsonUtils.toJson(fail(message));
    }

    public String menuError(String message) {
        return JsonUtils.toJson(error(message));
    }

}
