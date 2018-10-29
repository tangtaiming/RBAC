package com.system.core.domain;

/**
 * @auther ttm
 * @date 2018/10/29 0029
 **/
public interface Pageable {

    int getPageNumber();

    int getPageSize();

    int getOffset();

}
