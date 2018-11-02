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
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainAction<E> extends RbacAction {

    private Map<String, Object> main;

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
        try {
            super._execute();
            setMainXml(getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainXml(String url) throws ParsingException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
        setTitle(mainEntity.getTitle());
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

        PageSession pageSession = new PageSession();
        this.page = pageSession.getPage();
    }

}
