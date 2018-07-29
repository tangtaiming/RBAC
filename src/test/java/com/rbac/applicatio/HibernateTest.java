package com.rbac.applicatio;

import com.rbac.application.action.dto.UserRsDto;
import com.rbac.application.dao.RoleDao;
import com.rbac.application.orm.Role;
import com.rbac.application.service.UserService;
import com.system.core.dao.BaseDao;
import com.system.util.enumerate.RoleStatus;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

//    @Before
    public void initSession() {
//        Configuration configuration = new Configuration().configure();
//        sessionFactory = configuration.buildSessionFactory();
//        session = sessionFactory.openSession();
    }

//    @Test
    public void saveRole() {
        Transaction transaction = session.beginTransaction();
        Role role = new Role();
        role.setUpdateDate(time);
        role.setCreateDate(time);
        role.setStatus(RoleStatus.OPEN.getStatus());
        role.setName("总监");
        session.save(role);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

//    @Test
    public void findAllRole() {
        BaseDao roleDao = new RoleDao();
        roleDao.findAllList();
    }

//    @Test
    public void saveUser() {
        UserRsDto user = new UserRsDto();
        user.setName("xiaoming");
        user.setEmail("1252575758@qq.com");
        UserService userService = new UserService();
        userService.saveUser(user);
    }

//    public void re() {
//        DozerBeanMapper mapper = new
//                DozerBeanMapper();
//        mapper.map()
//    }

}
