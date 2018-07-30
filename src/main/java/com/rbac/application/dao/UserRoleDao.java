package com.rbac.application.dao;

import com.rbac.application.orm.UserRole;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28
 */
public class UserRoleDao extends BaseDao<UserRole> {

    public UserRoleDao(Class classes) {
        super(classes);
    }

    public List<UserRole> findUserRoleByUserId(Integer userId) {
        List<UserRole> userRoleList = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            Root root = criteriaQuery.from(getClasses());
            Expression expression = root.get("userId").as(Integer.class);
            Predicate predicate = criteriaBuilder.equal(expression, userId);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            userRoleList = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return userRoleList;
    }

    public List<Integer> findUserRoleColumnRoleIdByUserId(Integer userId) {
        List<Integer> userRoleIdList = new ArrayList<>();
        Session session = null;
        String hql = "select roleId from UserRole ur where ur.userId=:userId";
        try {
            session = HibernateUtils.getSession();
            Query query = session.createQuery(hql).setParameter("userId", userId);
            userRoleIdList = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return userRoleIdList;
    }

}
