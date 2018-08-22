package com.rbac.application.dao;

import com.rbac.application.orm.Access;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class AccessDao extends BaseDao<Access> {

    public AccessDao(Class classes) {
        super(classes);
    }

    public Access findAccessByTitle(String title) {
        Access access = null;
        Session session = HibernateUtils.getSession();
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            setSession(HibernateUtils.getSession());
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            Root root = criteriaQuery.from(getClasses());
            Expression expression = root.get("title").as(String.class);
            Predicate predicate = criteriaBuilder.equal(expression, title);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            List<Access> findRoleList = query.getResultList();
            if (CollectionUtils.isNotEmpty(findRoleList)) {
                access = findRoleList.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return access;
    }

}
