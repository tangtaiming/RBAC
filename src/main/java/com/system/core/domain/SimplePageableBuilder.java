package com.system.core.domain;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class SimplePageableBuilder {

    private Pageable pageable;

    public Pageable addPageable(Integer pageNumber, Integer pageSize) {
        pageable = new SimplePageable(pageNumber, pageSize);
        return pageable;
    }

}
