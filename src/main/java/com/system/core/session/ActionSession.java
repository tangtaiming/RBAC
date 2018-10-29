package com.system.core.session;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @auther ttm
 * @date 2018/10/27 0027
 **/
public class ActionSession {

    private ActionContext action;

    public ActionSession() {
        action = ServletActionContext.getActionContext(ServletActionContext.getRequest());
    }

    public ActionContext getAction() {
        return action;
    }

    public void setAction(ActionContext action) {
        this.action = action;
    }
}
