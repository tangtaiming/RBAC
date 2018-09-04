package com.rbac.application.service;

import com.rbac.application.dao.ReviewDao;
import com.rbac.application.orm.Review;

/**
 * @auther ttm
 * @date 2018/9/5 0005
 **/
public class ReviewService {

    private ReviewDao reviewDao = new ReviewDao();

    private boolean saveReview(Review review) {
        reviewDao.save(review);
        return !(null == review.getReviewId()) ? true : false;
    }

}
