package com.system.core.session;

import com.system.core.domain.Pageable;
import com.system.core.domain.SimplePageableBuilder;
import com.system.core.vo.PageVo;
import com.system.util.base.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/10/27 0027
 **/
public class PageSession {

    public final String PAGE = "page";

    /**
     * 分页工具
     */
    private PageUtils page;

    /**
     * 分页
     */
    private PageVo pageVo;

    /**
     * 默认第一页
     */
    public final static Integer PAGENUMBER = 1;

    /**
     * 默认每页数量
     */
    public final static Integer PAGESIZE = 1;

    /**
     * 分页构造器
     */
    private SimplePageableBuilder pageableBuilder;

    public PageSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        RbacSession rbacSession = new RbacSession();
        Map<String, Object> session = rbacSession.getSession();
        String uriPageSession = PAGE + "_" + requestUri;
        String method = request.getMethod();
        if (method.equals("POST")) {
            String pageNumber = request.getParameter("pageNumber");
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageNumber) || StringUtils.isEmpty(pageSize)) {
                throw new IllegalArgumentException("pageNumber and pageSize can not be null");
            }
            pageVo = new PageVo.PageVoBuilder(Integer.valueOf(pageNumber),
                    Integer.valueOf(pageSize)).build();
        } else {
            pageVo = (PageVo) session.get(uriPageSession);
            if (null == pageVo) {
                pageVo = new PageVo.PageVoBuilder(PAGENUMBER, PAGESIZE).build();
            }
        }

        //进行session存储
        session.put(uriPageSession, pageVo);
     }

    public PageUtils getPage() {
        return page;
    }

    public void setPage(PageUtils page) {
        this.page = page;
    }

    /**
     * 初始化分页查询条件
     * @return
     */
    public Pageable initPageableBuilder() {
        pageableBuilder = new SimplePageableBuilder();
        return pageableBuilder.addPageable(pageVo.getPageNumber(), pageVo.getPageSize());
    }
}
