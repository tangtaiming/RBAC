package com.system.core.domain;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public class SimplePageable implements Pageable {

    private int pageNumber;

    private int pageSize;

    public SimplePageable(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

}
