package com.system.core.session;

import com.system.core.vo.PageVo;
import com.system.util.base.PageUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
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
     * 如果请求是 GET请求
     * 获取pageSession中的page对象数据设置
     * 如果请求是 POST请求
     * 先获取当前页 与 每页显示数量 与 查询总数量之后得出page对象数据
     * @param pageNumber
     * @param pageSize
     * @param totalRows
     */
    public PageSession(Integer pageNumber, Integer pageSize, Integer totalRows) {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestUri = request.getRequestURI();
        RbacSession rbacSession = new RbacSession();
        Map<String, Object> session = rbacSession.getSession();
        String uriPageSession = PAGE + "_" + requestUri;

        String method = request.getMethod();
        if (method.equals("POST")) {
            pageVo = new PageVo(pageNumber, pageSize, totalRows);
            //计算分页
            page = new PageUtils(pageVo);
        } else {
            pageVo = (PageVo) session.get(uriPageSession);
            if (null == pageVo) {
                pageVo = new PageVo(PAGENUMBER, PAGESIZE, totalRows);
            }
            page = new PageUtils(pageVo);
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

}
