package com.rbac.application.service;

import com.system.core.dao.BaseDao;
import com.system.core.domain.*;
import com.system.core.session.FilterSession;
import com.system.core.session.PageSession;
import com.system.core.session.SortSession;
import com.system.util.base.PageUtils;

import java.util.List;

/**
 * @auther ttm
 * @date 2018/10/31 0031
 **/
public abstract class SimpleCoreService<T> {

    public SimpleCoreService() {}

    /**
     * 数据集合
     * @return
     */
    public abstract List<T> getDataList();

    /**
     * 分页条件
     * @return
     */
    public abstract PageUtils getPage();

    /**
     * 根据boolean 获取字符串结果 true 成功 / false 失败
     * @param falg
     * @return
     */
    public String getResult(boolean falg) {
        return falg ? "success" : "fail";
    }

}
