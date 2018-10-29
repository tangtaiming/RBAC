package com.rbac.application.dao;

import com.rbac.application.orm.Menu;
import com.system.core.dao.BaseDao;
import com.system.core.domain.SimpleSpecificationBuilder;
import com.system.core.domain.Specification;
import com.system.util.base.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/8/23
 */
public class MenuDao extends BaseDao<Menu> {

    private Integer button = 2;

    public MenuDao() {
        super(Menu.class);
    }

    public MenuDao(Class classes) {
        super(classes);
    }

    public List<Menu> queryNotButtonMenuList() {
        List<Menu> menuList = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            SimpleSpecificationBuilder builder = new SimpleSpecificationBuilder();
            Root root = criteriaQuery.from(getClasses());
//            Expression expression = root.get("type").as(Integer.class);
//            Predicate predicate = criteriaBuilder.notEqual(expression, button);
            Specification specification = builder.add("type", "=", button).generateSpecification();
            Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            menuList = query.list();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }
        return menuList;
    }

}
