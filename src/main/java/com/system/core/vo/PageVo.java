package com.system.core.vo;

/**
 * @auther ttm
 * @date 2018/10/27 0027
 **/
public final class PageVo {

    /**
     * 当前页
     */
    private Integer pageNumber;

    /**
     * 每页显示数量
     */
    private Integer pageSize;


    private PageVo(PageVoBuilder pageBuilder) {
        this.pageNumber = pageBuilder.pageNumber;
        this.pageSize = pageBuilder.pageSize;
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

    public static class PageVoBuilder {

        /**
         * 当前页
         */
        private final Integer pageNumber;

        /**
         * 每页显示数量
         */
        private final Integer pageSize;

        public PageVoBuilder(Integer pageNumber, Integer pageSize) {
            if (null == pageNumber || null == pageSize) {
                throw new IllegalArgumentException("pageNumber and pageSize can not be null");
            }
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public PageVo build() {
            return new PageVo(this);
        }

    }

}


