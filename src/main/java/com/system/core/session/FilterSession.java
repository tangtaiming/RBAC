package com.system.core.session;

import com.system.core.domain.SimpleSpecificationBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FilterSession {

    private Filter filter;

    private RbacSession session;

    private SimpleSpecificationBuilder specificationBuilder;

    public FilterSession() {
        filter = new Filter();
        session = new RbacSession();
        specificationBuilder = new SimpleSpecificationBuilder();
    }

    public SimpleSpecificationBuilder initSpecificationBuilder() {
        //POST请求
        HttpServletRequest request = ServletActionContext.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        if ("POST".equals(method)) {
            Enumeration params = request.getParameterNames();
            Map<String, Object> condition = new HashMap<>();
            while(params.hasMoreElements()) {
                String param = (String) params.nextElement();
                String value = trim(request.getParameter(param));
                if (continuePage(param)) {
                    continue;
                }

                condition.put(param, value);
                //解析规则
                parseExpression(param, value);
            }
            filter.setCondition(condition);
        }

        return specificationBuilder;
    }

    private void parseExpression(String param, String value) {
        //规则相比较
        List<String> compareList = expressionCompare(param);
        param = compareList.get(0);
        String compare = compareList.get(1);
        //规则类型
        List<String> castList = expressionCast(param);
        param = castList.get(0);
        String cast = castList.get(1);
        //构建条件
        buildExpression(param, compare, cast, value);
    }

    /**
     * 构建条件
     * @param param
     * @param compare
     * @param cast
     * @param value
     */
    private void buildExpression(String param, String compare, String cast, String value) {
        if (cast.equals("int")) {
            specificationBuilder.add(param, compare, Integer.valueOf(value));
        } else if (cast.equals("long")) {
            specificationBuilder.add(param, compare, Long.valueOf(value));
        } else if (cast.equals("float")) {
            specificationBuilder.add(param, compare, Float.valueOf(value));
        } else if (cast.equals("double")) {
            specificationBuilder.add(param, compare, Double.valueOf(value));
        } else {
            specificationBuilder.add(param, compare, value);
        }
    }

    private List<String> expressionCast(String param) {
        String cast = null;
        if (param.endsWith("[int]")) {
            param = sub(param, "[int]");
            cast = "int";
        } else if (param.endsWith("[long]")) {
            param = sub(param, "[long]");
            cast = "long";
        } else if (param.endsWith("[float]")) {
            param = sub(param, "[float]");
            cast = "float";
        } else if (param.endsWith("[double]")) {
            param = sub(param, "[double]");
            cast = "double";
        } else if (param.endsWith("[string]")) {
            param = sub(param, "[string]");
            cast = "string";
        } else {
            cast = "string";
        }

        List<String> caseList = new LinkedList<>();
        caseList.add(param);
        caseList.add(cast);
        return  caseList;
    }

    private List<String> expressionCompare(String param) {
        String compare = "=";
        if (param.endsWith("[from]")) {
            param = sub(param, "[from]");
            compare = ">=";
        } else if (param.endsWith("[to]")) {
            param = sub(param, "[to]");
            compare = "<=";
        } else if (param.endsWith("[like]")) {
            param = sub(param, "[like]");
            compare = ":";
        } else {
            //go to
        }

        List<String> compareList = new LinkedList<>();
        compareList.add(param);
        compareList.add(compare);
        return compareList;
    }

    private String sub(String param, String remove) {
        return param.substring(0, param.indexOf(remove));
    }

    /**
     * 去除空格
     * @param value
     * @return
     */
    private String trim(String value) {
        return StringUtils.replace(value, "\\s", "");
    }

    /**
     * 过滤分页条件
     * @param param
     * @return
     */
    private boolean continuePage(String param) {
        return param.equals("pageNumber") || param.equals("pageSize");
    }


}
