package com.rbac.application.dao;

import com.rbac.application.orm.RoleAccess;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/29 0029
 **/
public class RoleAccessDao extends BaseDao<RoleAccess> {

    public RoleAccessDao(Class classes) {
        super(classes);
    }

    public List<RoleAccess> findRoleAccessByRoleId(Integer roleId) {
        List<RoleAccess> roleAccessList = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            Root root = criteriaQuery.from(getClasses());
            Expression expression = root.get("roleId").as(Integer.class);
            Predicate predicate = criteriaBuilder.equal(expression, roleId);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            roleAccessList = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return roleAccessList;
    }

    public List<Integer> findRoleAccessColumnAccessIdByRoleId(Integer roleId) {
        List<Integer> accessList = new ArrayList<>();
        Session session = null;
        String hql = "select accessId from RoleAccess ra where ra.roleId=:roleId";
        try {
            session = HibernateUtils.getSession();
            Query query = session.createQuery(hql).setParameter("roleId", roleId);
            accessList = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return accessList;
    }


}
