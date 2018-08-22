package com.rbac.application.dao;

import com.rbac.application.orm.User;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/28
 */
public class UserDao extends BaseDao<User> {

    public UserDao(Class classes) {
        super(classes);
    }

    public User findUserByName(String name) {
        User user = null;
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            Root root = criteriaQuery.from(getClasses());
            Expression expression = root.get("name").as(String.class);
            Predicate predicate = criteriaBuilder.equal(expression, name);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            List<User> userList = query.getResultList();
            if (CollectionUtils.isNotEmpty(userList)) {
                user = userList.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return user;
    }

}
