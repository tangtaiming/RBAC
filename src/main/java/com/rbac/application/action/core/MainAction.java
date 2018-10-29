package com.rbac.application.action.core;

import com.system.core.parse.Main;
import com.system.core.session.FilterSession;
import com.system.core.session.PageSession;
import com.system.core.vo.FilterVo;
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
    private Integer pageNumber;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 总共数量
     */
    private int totalRows;

    /**
     * 集合
     */
    private List<E> collection;

    /**
     * 分页
     */
    private PageUtils page;

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
        FilterSession filterSession = new FilterSession();
        filterSession.getFilter();
        System.out.println("Show Filter...");
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
        main.put("entity", mainEntity.getClassesName());
    }

    public String getSubRequestUri() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestUri.substring(contextPath.length());
    }

    /**
     * 计算PAGE
     */
    public void calculationPage(int totalRows) {
        PageSession pageSession = new PageSession(pageNumber, pageSize, totalRows);
        page = pageSession.getPage();
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

    public List<E> getCollection() {
        return collection;
    }

    public void setCollection(List<E> collection) {
        this.collection = collection;
    }

    public PageUtils getPage() {
        return page;
    }

    public void setPage(PageUtils page) {
        this.page = page;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        initPage(totalRows);
        this.totalRows = totalRows;
    }

    private void initPage(int totalRows) {
        PageUtils page = new PageUtils(totalRows);
        setPage(page);
    }

}
