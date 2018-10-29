package com.system.core.vo;

/**
 * @auther ttm
 * @date 2018/10/27 0027
 **/
public class PageVo {

    /**
     * 当前页
     */
    private Integer pageNumber;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 总数量
     */
    private Integer totalRows;

    public PageVo() {}

    public PageVo(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public PageVo(Integer pageNumber, Integer pageSize, Integer totalRows) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }
}
