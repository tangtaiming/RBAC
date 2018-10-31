package com.system.util.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @auther ttm
 * @date 2018/7/28 0028
 **/
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    // 读取Hibernate配置文件并初始化SessionFactory
    static {
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        if (null != session && session.isOpen()) {
            session.close();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
