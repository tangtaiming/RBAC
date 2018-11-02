package com.rbac.application.action;

import com.rbac.application.action.core.RbacAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther ttm
 * @date 2018/7/24
 */
public class DefaultManagementAction extends RbacAction {

    private static Logger LOG = LoggerFactory.getLogger(DefaultManagementAction.class);

    /**
     * 默认首页
     * @return
     */
    public String home() {
        try {
            _execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("默认首页 Index...");
        return SUCCESS;
    }

}
