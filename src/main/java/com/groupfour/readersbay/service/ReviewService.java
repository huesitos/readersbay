package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;

import java.util.List;

public interface ReviewService{

    List<Review> fetchBookReviewById(Long bookId);
    public Review saveReview(Review review);
    public void deleteReviewById(Long reviewId);
}
