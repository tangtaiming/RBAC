package com.system.core.dao;

import com.system.core.domain.*;
import com.system.core.exception.RbacException;
import com.system.core.session.FilterSession;
import com.system.core.session.PageSession;
import com.system.core.session.SortSession;
import com.system.util.base.HibernateUtils;
import com.system.util.base.PageUtils;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    private Specification specification;

    private Orderable orderable;

    private Pageable pageable;

    public BaseDao(Class classes) {
        this.classes = classes;
    }

    public E findOneTest() {
        E entity = null;
        session = HibernateUtils.getSession();
        EntityManagerFactory factory = session.getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        entity = entityManager.find(classes, Long.valueOf(1));
        return entity;
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

    public void initSession() {
        FilterSession filterSession = new FilterSession();
        SimpleSpecificationBuilder filterBuilder = filterSession.initSpecificationBuilder();
        specification = filterBuilder.generateSpecification();
        //sort
        SortSession sortSession = new SortSession();
        Sort sort = sortSession.getSort();
        orderable = new SimpleOrderableBuilder<>().setJpaOrder(sort).getOrderable();
        //page
        PageSession pageSession = new PageSession();
        pageable = pageSession.initPageableBuilder();
    }

    public List<E> findDataList() {
        List<E> datas = new ArrayList<E>();
        try {
            initSession();
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            Root root = criteriaQuery.from(classes);
            Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
            Order order = orderable.toOrder(root, criteriaBuilder);
            //查询条件
            if (!(null == predicate)) {
                criteriaQuery.where(predicate);
            }
            //排序
            if (!(null == order)) {
                criteriaQuery.orderBy(order);
            }
            TypedQuery typedQuery = session.createQuery(criteriaQuery);

            //分页
            typedQuery.setFirstResult(pageable.getOffset());
            typedQuery.setMaxResults(pageable.getPageSize());
            datas = typedQuery.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return datas;
    }

    /**
     * 条件查询数量
     * @return
     */
    public PageUtils findPage() {
        int count = 0;
        PageUtils page = null;
        try {
            initSession();
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(classes);
            Root root = criteriaQuery.from(classes);
            criteriaQuery.select(criteriaBuilder.count(root.get("id")));
            Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
            if (!(null == predicate)) {
                criteriaQuery.where(predicate);
            }
            javax.persistence.Query typedQuery = session.createQuery(criteriaQuery);

            Object objCount = typedQuery.getSingleResult();
            if (!(null == objCount)) {
                count = Integer.valueOf(objCount.toString());
                page = new PageUtils(pageable.getPageNumber(), pageable.getPageSize(), count);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateUtils.closeSession(session);
        }

        return page;
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

    public E findEqOne(Map<String, Object> query) throws RbacException {
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
            String message = "Find " + classes.getName() + " " + noResultEx.getMessage();
            LOG.warn(message);
        } catch (NonUniqueResultException nonUniqueEx) {
            String message = "Find " + classes.getName() + " " + nonUniqueEx.getMessage();
            throw new RbacException(message);
        }catch (Exception e) {
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

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Orderable getOrderable() {
        return orderable;
    }

    public void setOrderable(Orderable orderable) {
        this.orderable = orderable;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
