package com.rbac.application.action;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class DefaultManagementAction extends ActionSupport {

    private static Logger LOG = LoggerFactory.getLogger(DefaultManagementAction.class);

    /**
     * 默认首页
     * @return
     */
    public String index() {
        LOG.info("默认首页 Index...");
        return SUCCESS;
    }

}
