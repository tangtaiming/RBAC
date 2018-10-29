package com.rbac.application.action.core;

import com.system.core.parse.Main;
import com.system.core.session.FilterSession;
import nu.xom.ParsingException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainAction extends RbacAction {

    private Map<String, Object> main;

    private String url;

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
}
