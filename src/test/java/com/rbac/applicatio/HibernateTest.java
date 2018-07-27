package com.rbac.applicatio;

import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Role;
import com.system.core.dao.BaseDao;
import com.system.util.enumerate.RoleStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther ttm
 * @date 2018/7/27
 */
public class HibernateTest {

    public SessionFactory sessionFactory;

    private Session session;

//    @Before
    public void initSession() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

//    @Test
    public void saveRole() {
        Transaction transaction = session.beginTransaction();
        Role role = new Role();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        role.setUpdateDate(time);
        role.setCreateDate(time);
        role.setStatus(RoleStatus.OPEN.getStatus());
        role.setName("总监");
        session.save(role);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void findAllRole() {
        BaseDao roleDao = new RoleDao();
        roleDao.findAllList();
    }

}
