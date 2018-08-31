package com.rbac.applicatio;

import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Menu;
import com.rbac.application.service.MenuService;
import com.rbac.application.service.UserService;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import com.system.util.base.JsonUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther ttm
 * @date 2018/7/27
 */
public class HibernateTest {

    public SessionFactory sessionFactory;

    private Session session;

    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

//    @Before
    public void initSession() {
//        Configuration configuration = new Configuration().configure();
//        sessionFactory = configuration.buildSessionFactory();
//        session = sessionFactory.openSession();
    }

//    @Test
    public void saveRole() {
//        Transaction transaction = session.beginTransaction();
//        Role role = new Role();
//        role.setUpdateDate(time);
//        role.setCreateDate(time);
//        role.setStatus(RoleStatus.OPEN.getStatus());
//        role.setName("总监");
//        session.save(role);
//        transaction.commit();
//        session.close();
//        sessionFactory.close();
    }

//    @Test
    public void findAllRole() {
        BaseDao roleDao = new RoleDao();
        roleDao.findAllList();
    }

//    @Test
    public void saveUser() {
//        UserReDto user = new UserReDto();
//        user.setName("xiaoming");
//        user.setEmail("1252575758@qq.com");
//        UserService userService = new UserService();
//        userService.saveUser(user);
    }

    @Ignore
    @Test
    public void findUserColunm() {
        UserService userService = new UserService();
        userService.findUserAllListCount();
//        String countQuery = String.format("select count(%s) from %s", new Object[]{"*", "%s"});
//        System.out.println("Show: " + countQuery);
    }


    @Ignore
    @Test
    public void findAllIf() {
        Session session = HibernateUtils.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Menu.class);
        Root root = criteriaQuery.from(Menu.class);
        Predicate predicate = criteriaBuilder.equal(root.get("menuId"), 1L);
        Predicate predicate1 = criteriaBuilder.equal(root.get("name"), "商品管理");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(predicate);
        predicates.add(predicate1);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
//        criteriaBuilder.equal(root.get("name"), "商品管理");
        Query query = session.createQuery(criteriaQuery);
        System.out.println("Show: " + JsonUtils.toJson(query.getResultList()));
    }

    @Test
    public void findMapQueryList() {
        MenuService menuService = new MenuService();
        Map<String, Object> query = new LinkedMap();
        query.put("menuId", 1L);
        query.put("name", "商品管理");
        System.out.println("Show: " + JsonUtils.toJson(menuService.findMenuList(query)));
    }

}
