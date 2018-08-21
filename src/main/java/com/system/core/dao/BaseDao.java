package com.system.core.dao;

import com.system.util.base.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/7/27 0027
 **/
public abstract class BaseDao<E extends Serializable> {

    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    private Class<E> classes;

    public BaseDao(Class classes) {
        this.classes = classes;
    }

    private E findOne(Serializable id) {
        E entity = null;
        try {
            session = HibernateUtils.getSession();
            entity = session.get(classes, id);
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return entity;
    }

    public E findOne(String id) {
        return findOne((Serializable) id);
    }

    public E findOne(Long id) {
        return findOne((Serializable) id);
    }

    public E findOne(Integer id) {
        return findOne((Serializable) id);
    }

    public int findAllListCount(Class classes) {
        setClasses(classes);
        return findAllListCount();
    }

    public int findAllListCount() {
        int count = 0;
        try {
            String countQuery = String.format("select count(*) from %s", classes.getSimpleName());
            session = HibernateUtils.getSession();
            Query query = session.createQuery(countQuery);
            Optional optional = query.uniqueResultOptional();
            if (optional.isPresent()) {
                count = Integer.valueOf(optional.get().toString());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return count;
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<E> findAllList() {
        List<E> datas = null;
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            criteriaQuery.from(classes);
            datas = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return datas;
    }

    public <T> void update(T obc) {
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(obc);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

    }

    public <T> Integer save(T obc) {
        Integer nextId = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            nextId = (Integer) session.save(obc);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return nextId;
    }

    public <T> boolean delete(T entity) {
        boolean deleteFalg = false;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            deleteFalg = true;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return deleteFalg;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Class<E> getClasses() {
        return classes;
    }

    public void setClasses(Class<E> classes) {
        this.classes = classes;
    }
}
