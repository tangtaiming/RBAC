package com.system.core.dao;

import com.system.util.base.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @auther ttm
 * @date 2018/7/27 0027
 **/
public abstract class BaseDao<E extends Serializable> {

    private Logger LOG = LoggerFactory.getLogger(BaseDao.class);

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

    /**
     * 条件查询只适用于 eq
     * @param query
     * @return
     */
    public List<E> findEqList(Map<String, Object> query) {
        List<E> datas = new ArrayList<E>();
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            Root root = criteriaQuery.from(classes);
            List<Predicate> queryPredicateList = new ArrayList<>();
            for (String key : query.keySet()) {
                Predicate predicate = criteriaBuilder.equal(root.get(key), query.get(key));
                queryPredicateList.add(predicate);
            }
            criteriaQuery.where(queryPredicateList.toArray(new Predicate[]{}));
            Query findQuery = session.createQuery(criteriaQuery);
            datas = findQuery.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return datas;
    }

    public E findEqOne(Map<String, Object> query) {
        E entity = null;
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            Root root = criteriaQuery.from(classes);
            List<Predicate> queryPredicateList = new ArrayList<>();
            for (String key : query.keySet()) {
                Predicate predicate = criteriaBuilder.equal(root.get(key), query.get(key));
                queryPredicateList.add(predicate);
            }
            criteriaQuery.where(queryPredicateList.toArray(new Predicate[]{}));
            Query findQuery = session.createQuery(criteriaQuery);
            entity = (E) findQuery.getSingleResult();
        } catch (NoResultException noResultEx) {
            LOG.warn("Find {} {}", classes.getName(), noResultEx.getMessage());
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return entity;
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

    public <T> Serializable save(T obc) {
        Serializable nextId;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            nextId = session.save(obc);
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
