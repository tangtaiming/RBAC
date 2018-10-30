package com.system.core.session;

import com.system.core.domain.Sort;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @auther ttm
 * @date 2018/10/31 0031
 **/
public class SortSession {

    private static final String SORT = "sort";

    private Sort sort;

    public SortSession() {
        defaultSortId();
        HttpServletRequest request = ServletActionContext.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String urlSortSession = SORT + "_" + uri;
        RbacSession session = new RbacSession();
        Sort lastSort = (Sort) session.get(urlSortSession);
        if (!(null == lastSort)) {
            sort = lastSort;
        }

        if ("POST".equals(method)) {
            //go to
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String param = (String) enumeration.nextElement();
                if (param.startsWith("sort-")) {
                    String value = request.getParameter(param);
                    sort = new Sort();
                    sort.setPropertyName(StringUtils.removeStart(param, "sort-"));
                    sort.setSort(value);
                    break;
                }
            }
        }

        session.put(urlSortSession, sort);
    }

    /**
     * 默认id 升序
     * @return
     */
    public void defaultSortId() {
        sort = new Sort();
        sort.setSort("asc");
        sort.setPropertyName("id");
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
