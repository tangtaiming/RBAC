package com.system.core.session;

import com.system.core.vo.FilterVo;
import com.system.util.base.ServiceQueryUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * @auther ttm
 * @date 2018/10/28 0028
 **/
public class FilterSession {

    private final String FILTER = "filter";
    private final String ADVANCED_TAG = "[advanced]";
    private HashMap<String, FilterVo> filterMap;
    private FilterVo filter;
    private RbacSession session = new RbacSession();

    public FilterVo getFilter() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HashMap fm = (HashMap) session.get(FILTER);
        String requestUri = request.getRequestURI();

        return (FilterVo) fm.get(requestUri);
    }

    public void setFilter(FilterVo filter) {
        this.filter = filter;
    }

    public FilterSession() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String requestUri = request.getRequestURI();
            if (session.get(FILTER) != null) {
                filterMap = (HashMap) session.get(FILTER);
                filter = filterMap.get(requestUri);
                if (null == filter) {
                    filter = new FilterVo();
                    filterMap.put(requestUri, filter);
                }
            } else {
                filterMap = new HashMap<>();
                filter = new FilterVo();
                filterMap.put(requestUri, filter);
                session.put(FILTER, filterMap);
            }
            if ("POST".equals(request.getMethod())) {
                ArrayList<Criterion> restrictions = new ArrayList<Criterion>();
                filter.setQuery(new HashMap<>());
                Map<String, List<Criterion>> criterias = new HashMap<String, List<Criterion>>();
                Enumeration params = request.getParameterNames();
                String queryString = request.getQueryString();
//                String sorterValue = request.getParameter("sorter-key");
                Map conditions = new HashMap();
                while (params.hasMoreElements()) {
                    String param = (String) params.nextElement();
                    String value = request.getParameter(param);
                    if (queryString != null && queryString.indexOf(param + '=') > -1) {
                        continue;
                    }
                    if (param.startsWith("pager-")) {
                        continue;
                    }
                    if (param.equals("_")) {
                        continue;
                    }
                    if (param.startsWith("sorter-")) {
                        continue;
                    }

                    Boolean isAdvancedParam = false;
                    if (param.endsWith(ADVANCED_TAG)) {
                        param = param.substring(0, param.indexOf(ADVANCED_TAG));
                        isAdvancedParam = true;
                    } else {
                        conditions.put(param, value);
                        conditions.remove(param + ADVANCED_TAG);
                    }
                    conditions.put(param, value);

                    String multiParams[] = param.split("\\|");
                    if (multiParams.length ==1) {
                        String dotParams[] = param.split("\\.");
                        Criterion simpleExpression = null;
                        if (dotParams.length == 1) {
                            if (isAdvancedParam && value.split(",").length > 1) {
                                simpleExpression = _parseSimpleExpression(param, value.replaceAll("\\s", "").split(","));
                            } else {
                                simpleExpression = _parseSimpleExpression(param, value);
                            }
                        } else if (dotParams.length >= 2) {
                            criterias = _parseDotParams(criterias, param, value);
                        }
                        if (simpleExpression != null) {
                            restrictions.add(simpleExpression);
                        }
                    } else {
                        Criterion simpleExpression = null;
                        Disjunction disjunction = Restrictions.disjunction();
                        for (String splitParam: multiParams) {
                            splitParam = splitParam + "[like]";
                            simpleExpression = disjunction.add(_parseSimpleExpression(splitParam, value));
                        }
                        if (simpleExpression != null) {
                            restrictions.add(simpleExpression);
                        }
                    }
                }
//                _parseSorterValue(sorterValue, criterias);
                filter.setRestrictions(restrictions);
                filter.setCriterias(criterias);
                filter.setConditions(conditions);
                filterMap.put(requestUri, filter);

	            /* a orm class bug here
	            String toRemoveRequestUri = new PageDequeSingleton().getToRemoveRequestUri();
	            if (toRemoveRequestUri != null && ! toRemoveRequestUri.equals(requestUri)) {
	                filterMap.remove(toRemoveRequestUri);
	            } */
                session.put(FILTER, filterMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRestrictionsAttribute() {
        return FILTER;
    }

//    private void _parseSorterValue(String param, Map<String, List<Criterion>> criterias) {
//        if (null == param) {
//            SorterSession sorterSession = new SorterSession(null);
//            if (null != sorterSession.getSorter()) {
//                param = sorterSession.getSorter().getKey();
//            }
//        }
//        if (null == param) {
//            return;
//        }
//        if (param.indexOf("[") > 0) {
//            param = param.substring(0, param.indexOf("["));
//        }
//        String dotParams[] = param.split("\\.");
//        int lastIndex = param.lastIndexOf(".");
//        if (lastIndex < 0) {
//            return;
//        }
//        String lastParam = param.substring(0, lastIndex);
//
//        if ( ! criterias.containsKey(lastParam)) {
//            criterias.put(lastParam, null);
//        }
//        String tmpDotParam = "";
//        for (int i = 0; i < dotParams.length - 2; i++) {
//            if ("".equals(tmpDotParam)) {
//                tmpDotParam = dotParams[i];
//            } else {
//                tmpDotParam += "." + dotParams[i];
//            }
//            if ( ! criterias.containsKey(tmpDotParam)) {
//                criterias.put(tmpDotParam, null);
//            }
//        }
//    }

    private Map<String, List<Criterion>> _parseDotParams(Map<String,
            List<Criterion>> criterias, String param, String value)
            throws ClassNotFoundException, NoSuchFieldException {
        String dotParams[] = param.split("\\.");
        int lastIndex = param.lastIndexOf(".");
        String lastParam = param.substring(0, lastIndex);
        List<Criterion> secondRstrictionList = criterias.get(lastParam);
        if (null == secondRstrictionList) {
            secondRstrictionList = new ArrayList<Criterion>();
        }
        String values[] = value.split(",");

        param = lastParam.replace(".", "_") + param.substring(lastIndex);

        Criterion secondsimpleEx = null;
        if (values.length > 1) {
            secondsimpleEx = _parseSimpleExpression(param, values);
        } else {
            secondsimpleEx = _parseSimpleExpression(param, value);
        }
        if (secondsimpleEx != null) {
            secondRstrictionList.add(secondsimpleEx);
        }
        criterias.put(lastParam, secondRstrictionList);
        String tmpDotParam = "";
        for (int i = 0; i < dotParams.length - 2; i++) {
            if ("".equals(tmpDotParam)) {
                tmpDotParam = dotParams[i];
            } else {
                tmpDotParam += "." + dotParams[i];
            }
            List<Criterion> dotRstrictionList = criterias.get(tmpDotParam);
            criterias.put(tmpDotParam, dotRstrictionList);
        }

        return criterias;
    }

    private Criterion _parseSimpleExpression(String param, String value)
            throws ClassNotFoundException, NoSuchFieldException {
        Criterion simpleExpression;
        List<String> compareResult = _parseSimpleExpressionCompare(param);
        param = compareResult.get(0);
        String compare = compareResult.get(1);
        List<String> castResult = _parseSimpleExpressionCast(param);
        param = castResult.get(0);
        String cast = castResult.get(1);
        simpleExpression = _innerParseSimpleExpressionCast(param, value, compare, cast);

        return simpleExpression;
    }

    private Criterion _parseSimpleExpression(String param, String[] values)
            throws ClassNotFoundException, NoSuchFieldException {
        Criterion simpleExpression;
        String compare;
        if (values.length > 1) {
            compare = "in";
        } else {
            List<String> compareResult = _parseSimpleExpressionCompare(param);
            param = compareResult.get(0);
            compare = compareResult.get(1);
        }
        List<String> castResult = _parseSimpleExpressionCast(param);
        param = castResult.get(0);
        String cast = castResult.get(1);
        simpleExpression = _innerParseCriterion(param, values, compare, cast);

        return simpleExpression;
    }

    protected List<String> _parseSimpleExpressionCompare(String param) {
        String compare = "eq";
        if (param.endsWith("[from]")) {
            param = param.substring(0, param.indexOf("[from]"));
            compare = "ge";
        } else if (param.endsWith("[to]")) {
            param = param.substring(0, param.indexOf("[to]"));
            compare = "le";
        } else if (param.endsWith("[like]")) {
            param = param.substring(0, param.indexOf("[like]"));
            compare = "like";
        } else if (param.endsWith("[null]")) {
            param = param.substring(0, param.indexOf("[null]"));
            compare = "null";
        } else if (param.endsWith("[!null]")) {
            param = param.substring(0, param.indexOf("[!null]"));
            compare = "!null";
        } else if (param.endsWith("[true]")) {
            param = param.substring(0, param.indexOf("[true]"));
            compare = "true";
        } else if (param.endsWith("[!true]")) {
            param = param.substring(0, param.indexOf("[!true]"));
            compare = "!true";
        }

        List<String> result = new ArrayList<String>();
        result.add(param);
        result.add(compare);
        return result;
    }

    protected List<String> _parseSimpleExpressionCast(String param)
            throws ClassNotFoundException, NoSuchFieldException {
        List<String> result = new ArrayList<String>();
        String cast;
        if (param.endsWith("[int]")) {
            param = param.substring(0, param.indexOf("[int]"));
            cast = "int";
        } else if (param.endsWith("[long]")) {
            param = param.substring(0, param.indexOf("[long]"));
            cast = "long";
        } else if (param.endsWith("[float]")) {
            param = param.substring(0, param.indexOf("[float]"));
            cast = "float";
        } else if (param.endsWith("[double]")) {
            param = param.substring(0, param.indexOf("[double]"));
            cast = "double";
        } else if (param.endsWith("[string]")) {
            param = param.substring(0, param.indexOf("[string]"));
            cast = "string";
        } else {
            String ormClass = getFilter().getOrmClass();
            if (null == ormClass) {
                cast = "string";
            } else {
                cast = Class.forName(ormClass).getDeclaredField(param).getType().getName();
            }
        }
        result.add(param);
        result.add(cast);

        return result;
    }

    private void _saveFilterQuery(String key, Object value, String compare) {
        if (null == compare) {
            filter.getQuery().put(key, value);
        } else {
            Map<String, Object> query = filter.getQuery();
            Map<String, Object> _innerQuery = new LinkedHashMap<String, Object>();
            if (query.containsKey(key)) {
                _innerQuery = (Map<String, Object>) query.get(key);
            }
            _innerQuery.put(compare, value);
            query.put(key, _innerQuery);
        }
    }

    protected Criterion _innerParseSimpleExpressionCast(String param, String value, String compare, String cast) {
        Criterion simpleExpression;
        try {
            if (cast.equals("int") || cast.equals("java.lang.Integer")) {
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, Integer.valueOf(value));
                    _saveFilterQuery(param, Integer.valueOf(value), ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, Integer.valueOf(value));
                    _saveFilterQuery(param, Integer.valueOf(value), ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, Integer.valueOf(value));
                    _saveFilterQuery(param, Integer.valueOf(value), null);
                }
            } else if (cast.equals("long") || cast.equals("java.lang.Long")) {
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, Long.valueOf(value));
                    _saveFilterQuery(param, Long.valueOf(value), ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, Long.valueOf(value));
                    _saveFilterQuery(param, Long.valueOf(value), ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, Long.valueOf(value));
                    _saveFilterQuery(param, Long.valueOf(value), null);
                }
            } else if (cast.equals("short") || cast.equals("java.lang.Short")) {
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, Short.valueOf(value));
                    _saveFilterQuery(param, Short.valueOf(value), ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, Short.valueOf(value));
                    _saveFilterQuery(param, Short.valueOf(value), ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, Short.valueOf(value));
                    _saveFilterQuery(param, Short.valueOf(value), null);
                }
            } else if (cast.equals("float") || cast.equals("java.lang.Float")) {
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, Float.valueOf(value));
                    _saveFilterQuery(param, Float.valueOf(value), ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, Float.valueOf(value));
                    _saveFilterQuery(param, Float.valueOf(value), ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, Float.valueOf(value));
                    _saveFilterQuery(param, Float.valueOf(value), null);
                }
            } else if (cast.equals("double") || cast.equals("java.lang.Double")) {
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, Double.valueOf(value));
                    _saveFilterQuery(param, Double.valueOf(value), ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, Double.valueOf(value));
                    _saveFilterQuery(param, Double.valueOf(value), ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, Double.valueOf(value));
                    _saveFilterQuery(param, Double.valueOf(value), null);
                }
            } else if (cast.equals("date") || cast.equals("java.util.Date")) {
                Timestamp date = Timestamp.valueOf(value);
                if (compare.equals("ge")) {
                    simpleExpression = Restrictions.ge(param, date);
                    _saveFilterQuery(param, date, ServiceQueryUtils.GE);
                } else if (compare.equals("le")) {
                    simpleExpression = Restrictions.le(param, date);
                    _saveFilterQuery(param, date, ServiceQueryUtils.LE);
                } else {
                    simpleExpression = Restrictions.eq(param, date);
                    _saveFilterQuery(param, date, null);
                }
            } else {
                if (compare.equals("like")) {
                    simpleExpression = Restrictions.ilike(param, "%" + value + "%");
                    _saveFilterQuery(param, value, ServiceQueryUtils.LIKE);
                } else if (compare.equals("null")) {
                    simpleExpression = Restrictions.isNull(param);
                    _saveFilterQuery(param, value, ServiceQueryUtils.NULL);
                } else if (compare.equals("!null")) {
                    simpleExpression = Restrictions.isNotNull(param);
                    _saveFilterQuery(param, value, ServiceQueryUtils.NOT_NULL);
                } else if (compare.equals("true")) {
                    simpleExpression = Restrictions.eq(param, true);
                } else if (compare.equals("!true")) {
                    simpleExpression = Restrictions.ne(param, true);
                } else {
                    if (compare.equals("ge")) {
                        simpleExpression = Restrictions.ge(param, value);
                        _saveFilterQuery(param, value, ServiceQueryUtils.GE);
                    } else if (compare.equals("le")) {
                        simpleExpression = Restrictions.le(param, value);
                        _saveFilterQuery(param, value, ServiceQueryUtils.LE);
                    } else {
                        simpleExpression = Restrictions.eq(param, value);
                        _saveFilterQuery(param, value, null);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            simpleExpression = null;
        }

        return simpleExpression;
    }

    private Criterion _innerParseCriterion(String param, String[] values, String compare, String cast) {
        Criterion criterion = null;
        try {
            List result = new ArrayList();
            if (cast.equals("int") || cast.equals("java.lang.Integer")) {
                for (String value: values) {
                    result.add(Integer.valueOf(value));
                }
            } else if (cast.equals("long") || cast.equals("java.lang.Long")) {
                for (String value: values) {
                    result.add(Long.valueOf(value));
                }
            } else if (cast.equals("short") || cast.equals("java.lang.Short")) {
                for (String value: values) {
                    result.add(Short.valueOf(value));
                }
            } else if (cast.equals("float") || cast.equals("java.lang.Float")) {
                for (String value: values) {
                    result.add(Float.valueOf(value));
                }
            } else if (cast.equals("double") || cast.equals("java.lang.Double")) {
                for (String value: values) {
                    result.add(Double.valueOf(value));
                }
            } else if (cast.equals("date") || cast.equals("java.util.Date")) {
                for (String value: values) {
                    result.add(Timestamp.valueOf(value));
                }
            } else {
                for (String value: values) {
                    result.add(value);
                }
                _saveFilterQuery(param, values, ServiceQueryUtils.IN);
            }
            Disjunction disjunction = Restrictions.disjunction();
            if (compare.equals("like")) {
                for (Object value: result) {
                    criterion = disjunction.add(Restrictions.ilike(param, "%" + value + "%"));
                }
            } else {
                for (Object value: result) {
                    criterion = disjunction.add(Restrictions.eq(param, value));
                }
            }
        } catch (NumberFormatException ex) {
            criterion = null;
        }

        return criterion;
    }

}
