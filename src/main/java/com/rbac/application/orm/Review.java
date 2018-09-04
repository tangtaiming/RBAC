package com.rbac.application.orm;

import java.io.Serializable;

/**
 * 复习任务表
 * @auther ttm
 * @date 2018/9/5 0005
 **/
public class Review implements Serializable {

    /**
     * ID
     */
    private Long reviewId;

    /**
     * 复习别名
     */
    private String name;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 复习时间
     */
    private String reviewDate;

    /**
     * 描述/消息/备注
     */
    private String message;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
