package com.rbac.application.dao;

import com.rbac.application.orm.Role;
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
 * @date 2018/7/27 0027
 **/
public class RoleDao extends BaseDao<Role> {

    private Class<Role> classes;

    public RoleDao() {
        super(Role.class);
        this.classes = Role.class;
    }

    public Role findRoleByName(String name) {
        Role role = null;
        Session session = HibernateUtils.getSession();
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            setSession(HibernateUtils.getSession());
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            Root root = criteriaQuery.from(classes);
            Expression expression = root.get("name").as(String.class);
            Predicate predicate = criteriaBuilder.equal(expression, name);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            List<Role> findRoleList = query.getResultList();
            if (CollectionUtils.isNotEmpty(findRoleList)) {
                role = findRoleList.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return role;
    }

}
