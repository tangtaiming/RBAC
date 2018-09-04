package com.rbac.application.dao;

import com.rbac.application.orm.Review;
import com.system.core.dao.BaseDao;

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

}
