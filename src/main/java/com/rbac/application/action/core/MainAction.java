package com.rbac.application.action.core;

import com.system.core.parse.Main;
import com.system.core.session.FilterSession;
import com.system.core.session.PageSession;
import com.system.util.base.PageUtils;
import nu.xom.ParsingException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainAction<E> extends RbacAction {

    private Map<String, Object> main;

    private String url;

    /**
     * 当前页
     */
    private int pageNumber;

    /**
     * 每页显示数量
     */
    private int pageSize;

    /**
     * 分页实体
     */
    private PageUtils page;


    /**
     * 获取数据
     */
    private List<E> dataList;

    @Override
    public void _execute() {
        super._execute();
        try {
            setMainXml(getUrl());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainXml(String url) throws ParsingException, IOException {
        Main mainEntity = null;
        if (StringUtils.isEmpty(url)) {
            mainEntity = new Main(getSubRequestUri());
        } else {
            mainEntity = new Main(url);
        }
        main = new HashMap<>();
        main.put("head", mainEntity.getHead());
        main.put("body", mainEntity.getBody());
        main.put("titleOne", mainEntity.getTitle());
    }

    public String getSubRequestUri() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestUri.substring(contextPath.length());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getMain() {
        return main;
    }

    public void setMain(Map<String, Object> main) {
        this.main = main;
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

    public PageUtils getPage() {
        return page;
    }

    public void setPage(PageUtils page) {
        this.page = page;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public void buildPageData(List<E> dataList, Integer totalRows) {
        this.dataList = dataList;
        if (0 < pageNumber) {
            pageNumber = 1;
        }
        if (0 < pageSize) {
            pageNumber = 20;
        }

        PageSession pageSession = new PageSession(pageNumber, pageSize, totalRows);
        this.page = pageSession.getPage();
    }

}
