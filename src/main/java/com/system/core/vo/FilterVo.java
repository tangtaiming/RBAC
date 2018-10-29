package com.system.core.vo;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/10/28 0028
 **/
public class FilterVo implements Serializable {
    private ArrayList<Criterion> restrictions = new ArrayList();
    Map<String, List<Criterion>> criterias = new HashMap();
    Map<String, Object> query = new HashMap();
    Map conditions = new HashMap();
    String ormClass;

    public ArrayList<Criterion> getRestrictions() {
        return this.restrictions;
    }

    public void setRestrictions(ArrayList<Criterion> restrictions) {
        this.restrictions = restrictions;
    }

    public Map<String, Object> getQuery() {
        return this.query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }

    public Map<String, List<Criterion>> getCriterias() {
        return this.criterias;
    }

    public void setCriterias(Map<String, List<Criterion>> criterias) {
        this.criterias = criterias;
    }

    public Map getConditions() {
        return this.conditions;
    }

    public void setConditions(Map conditions) {
        this.conditions = conditions;
    }

    public String getOrmClass() {
        return this.ormClass;
    }

    public void setOrmClass(String ormClass) {
        this.ormClass = ormClass;
    }
}
