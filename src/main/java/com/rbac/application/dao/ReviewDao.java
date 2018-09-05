package com.rbac.application.dao;

import com.rbac.application.orm.Review;
import com.system.core.dao.BaseDao;
import com.system.util.base.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/9/5 0005
 **/
public class ReviewDao extends BaseDao<Review> {

    public ReviewDao() {
        super(Review.class);
    }

    public ReviewDao(Class classes) {
        super(classes);
    }

    public List<Review> findReviewByReviewDate(String startDateTime, String endDateTime) {
        List<Review> reviewList = new ArrayList<>();
        Session session = null;
//        LocalDateTime localDateTime = LocalDateTime.parse(reviewDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
//        LocalDate localDate = localDateTime.toLocalDate();
//        String startDateTime = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + " 00:00:00";
//        String endDateTime = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + " 23:59:59";ww
        try {
            session = HibernateUtils.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(getClasses());
            Root root = criteriaQuery.from(getClasses());
            Expression expression = root.get("reviewDate");
            Predicate predicate = criteriaBuilder.between(expression, startDateTime, endDateTime);
            criteriaQuery.where(predicate);
            Query query = session.createQuery(criteriaQuery);
            reviewList = query.getResultList();
        } catch (Exception e) {
            throw e;
        }

        return reviewList;
    }
}
