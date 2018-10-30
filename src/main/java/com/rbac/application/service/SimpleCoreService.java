package com.rbac.application.service;

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

    private Specification specification;

    private Orderable orderable;

    private Pageable pageable;

    public SimpleCoreService() {
        FilterSession filterSession = new FilterSession();
        SimpleSpecificationBuilder filterBuilder = filterSession.initSpecificationBuilder();
        specification = filterBuilder.generateSpecification();
        //sort
        SortSession sortSession = new SortSession();
        Sort sort = sortSession.getSort();
        orderable = new SimpleOrderableBuilder<>().setJpaOrder(sort).getOrderable();
        //page
        PageSession pageSession = new PageSession();
        pageable = pageSession.initPageableBuilder();
    }

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

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Orderable getOrderable() {
        return orderable;
    }

    public void setOrderable(Orderable orderable) {
        this.orderable = orderable;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
