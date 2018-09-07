package com.rbac.application.service;

import com.rbac.application.dao.ReviewDao;
import com.rbac.application.orm.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @auther ttm
 * @date 2018/9/5 0005
 **/
public class ReviewService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMAT_2 = "yyyy-MM-dd";

    private ReviewDao reviewDao = new ReviewDao();

    public boolean saveReview(Review review) {
        reviewDao.save(review);
        return !(null == review.getReviewId()) ? true : false;
    }

    public List<Review> findReivewByReviewDate(String reviewDate) {
        LocalDateTime localDateTime = LocalDateTime.parse(reviewDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate localDate = localDateTime.toLocalDate();
        String startDateTime = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_2)) + " 00:00:00";
        String endDateTime = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_2)) + " 23:59:59";
        return reviewDao.findReviewByReviewDate(startDateTime, endDateTime);
    }

}
