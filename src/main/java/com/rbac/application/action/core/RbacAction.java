package com.rbac.application.action.core;

import com.opensymphony.xwork2.ActionSupport;
import com.system.core.vo.NavigatorRsVo;
import com.system.util.base.PageUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/31
 */
public class RbacAction extends ActionSupport {

    /**
     * 导航菜单
     */
    private NavigatorRsVo nav;

    /**
     * 分页
     */
    private PageUtils page;

    /**
     * 登录用户
     */
    private String loginName;

    /**
     * 总共数量
     */
    private int totalRows;

    /**
     * 获取数据
     */
    private List<?> dataList;

    private String time;

    public void _execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (!(null == session)) {
            String loginName = (String) session.getAttribute("name");
            setLoginName(loginName);
        }
        //实例化导航
        nav = new NavigatorRsVo();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public NavigatorRsVo getNav() {
        return nav;
    }

    public void setNav(NavigatorRsVo nav) {
        this.nav = nav;
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

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void setTime(String time) {
        this.time = time;
    }
}
