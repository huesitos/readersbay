package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;

public interface ReviewService{

    public Review fetchReviewById(Long reviewId) throws ReviewNotFoundException;
    public Review saveReview(Review review);
}
