package com.system.util.base;

import com.system.core.vo.PageVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/21
 */
public class PageUtils {

    /**
     * 当前页
     */
    private int pageNumber = 1;

    /**
     * 每页显示行数
     */
    private int pageSize;

    /**
     * 总数量
     */
    private int totalRows;

    /**
     * 分页数
     */
    private int totalPages;

    /**
     * 分页栏每页显示数量
     */
    private int rollPages = 11;

    /**
     * 是否下一页
     */
    private boolean downRow;

    /**
     * 是否上一页
     */
    private boolean upRow;

    /**
     * 是否第一页
     */
    private boolean firstRow;

    /**
     * 是否最后一页
     */
    private boolean endRow;

    private int upNumber;

    private int downNumber;

    private int firstNumber;

    private int endNumber;

    /**
     * 数字分页连接
     */
    private List<Integer> linkPages;

    /**
     * 默认当前页是1 每页显示数量为 20
     * @param totalRows
     */
    public PageUtils(int totalRows) {
        this(1, 20, totalRows);
    }

    /**
     * 默认每页数量 为20
     * @param pageNumber
     * @param totalRows
     */
    public PageUtils(int pageNumber, int totalRows) {
        this(pageNumber, 20, totalRows);
    }

    /**
     * 分页构造方法
     * @param pageNumber        当前页
     * @param pageSize          每页显示数量
     * @param totalRows         总共数量
     */
    public PageUtils(int pageNumber, int pageSize, int totalRows) {
        //当前页
        this.pageNumber = initPageNumber(pageNumber);
        //每页显示数量
        this.pageSize = pageSize;
        //初始化总页数
        this.totalPages = initTotalPages(totalRows, pageSize);
        //总共数量
        this.totalRows = totalRows;
        //第一页
        initFirstRow(this.pageNumber, this.totalPages, this.rollPages);
        //最后一页
        initEndRow(this.pageNumber, this.totalPages, this.rollPages);
        //上一页
        initUpRow(this.pageNumber);
        //下一页
        initDownRow(this.pageNumber, totalPages);
        //数字分页
        this.linkPages = linkPages(pageNumber, totalPages, rollPages);
    }

    /**
     * 初始化 当前页
     * @param pageNumber
     * @return
     */
    private int initPageNumber(int pageNumber) {
        return pageNumber > 0 ? pageNumber : 1;
    }

    /**
     * 初始化 总页数
     * @param totalRows
     * @param pageSize
     * @return
     */
    private int initTotalPages(int totalRows, int pageSize) {
        return (totalRows + pageSize - 1) / pageSize;
    }

    /**
     * 上一页
     * @param pageNumber
     * @return
     */
    private void initUpRow(int pageNumber) {
        upNumber = pageNumber - 1;
        if (upNumber > 0) {
            upRow = true;
        } else {
            upRow = false;
        }
    }

    /**
     * 下一页
     * @param pageNumber
     * @return
     */
    private void initDownRow(int pageNumber, int totalPages) {
        downNumber = pageNumber + 1;
        if (downNumber < totalPages) {
            downRow = true;
        } else {
            downRow = false;
        }
    }

    /**
     * 第一页
     * @return
     */
    private void initFirstRow(int pageNumber, int totalPages, int rollPages) {
        int tmpRollPage = rollPages / 2;
        if (totalPages > rollPages && (pageNumber - tmpRollPage) >= 1) {
            firstNumber = 1;
            firstRow = true;
        } else {
            firstNumber = 0;
            firstRow = false;
        }
    }

    /**
     * 最后一页
     * @return
     */
    private void initEndRow(int pageNumber, int totalPages, int rollPages) {
        int tmpRollPage = rollPages / 2;
        if ((totalPages > rollPages && (pageNumber + tmpRollPage) < totalPages)) {
            endNumber = totalPages;
            endRow = true;
        } else {
            endNumber = 0;
            endRow = false;
        }
    }

    /**
     * 初始化 数字连接集合
     * @param pageNumber
     * @param totalPages
     * @param rollPages
     * @return
     */
    private List<Integer> linkPages(int pageNumber, int totalPages, int rollPages) {
        List<Integer> linkPages = new ArrayList<>();
        if (totalPages <= 0) {
            return linkPages;
        }
        int tmpRollPage = rollPages / 2;
        int tmpPageNumber = pageNumber;
        if (totalPages > 0 && tmpPageNumber > totalPages) {
            tmpPageNumber = totalPages;
        }
        for (int page = 1; page <= rollPages; page++) {
            int currentPage = 0;
            if ((tmpPageNumber - tmpRollPage) <= 0) {
                currentPage = page;
            } else if ((tmpPageNumber + tmpRollPage - 1) >= totalPages) {
                currentPage = (totalPages - rollPages + page);
            } else {
                currentPage = (tmpPageNumber - tmpRollPage + page);
            }
            if (currentPage > 0 && currentPage <= totalPages) {
                linkPages.add(currentPage);
            }
        }
        return linkPages;
    }

    /**
     * 下一页
     * @return
     */
    public PageUtils next() {
        return isDownRow() ? new PageUtils(pageNumber + 1, pageSize, totalRows) : this;
    }

    /**
     * 上一页
     * @return
     */
    public PageUtils previous() {
        return isUpRow() ? new PageUtils(pageNumber - 1, pageSize, totalRows) : this;
    }

    /**
     * 第一页
     * @return
     */
    public PageUtils first() {
        return new PageUtils(1, pageSize, totalRows);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRollPages() {
        return rollPages;
    }

    public void setRollPages(int rollPages) {
        this.rollPages = rollPages;
    }

    public boolean isDownRow() {
        return downRow;
    }

    public void setDownRow(boolean downRow) {
        this.downRow = downRow;
    }

    public boolean isUpRow() {
        return upRow;
    }

    public void setUpRow(boolean upRow) {
        this.upRow = upRow;
    }

    public boolean isFirstRow() {
        return firstRow;
    }

    public void setFirstRow(boolean firstRow) {
        this.firstRow = firstRow;
    }

    public boolean isEndRow() {
        return endRow;
    }

    public void setEndRow(boolean endRow) {
        this.endRow = endRow;
    }

    public int getUpNumber() {
        return upNumber;
    }

    public void setUpNumber(int upNumber) {
        this.upNumber = upNumber;
    }

    public int getDownNumber() {
        return downNumber;
    }

    public void setDownNumber(int downNumber) {
        this.downNumber = downNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(int endNumber) {
        this.endNumber = endNumber;
    }

    public List<Integer> getLinkPages() {
        return linkPages;
    }

    public void setLinkPages(List<Integer> linkPages) {
        this.linkPages = linkPages;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", rollPages=" + rollPages +
                ", downRow=" + downRow +
                ", upRow=" + upRow +
                ", firstRow=" + firstRow +
                ", endRow=" + endRow +
                ", upNumber=" + upNumber +
                ", downNumber=" + downNumber +
                ", firstNumber=" + firstNumber +
                ", endNumber=" + endNumber +
                ", linkPages=" + linkPages +
                '}';
    }

}
