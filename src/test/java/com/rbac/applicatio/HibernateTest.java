package com.rbac.applicatio;

import com.rbac.application.orm.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/7/27
 */
public class HibernateTest {

    private Session session;

    private SessionFactory sessionFactory;

    @Before
    public void initConfig() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public void findRole() {
        // 开启事务
        session.beginTransaction();
        // 根据业务来编写代码
        Query query = session.createQuery("from Role"); // HQL语句，它类似于SQL语句
        List<Role> list = query.list();
        System.out.println(list);
        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void saveRole() {
        session.beginTransaction();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        Role role = new Role();
        role.setUpdateDate(time);
        role.setCreateDate(time);
        role.setName("总监");
        role.setStatus(1);
        Integer id = (Integer) session.save(role);
        System.out.println("Id: " + id);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}
