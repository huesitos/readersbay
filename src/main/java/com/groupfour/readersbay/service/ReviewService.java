package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;

import java.util.List;

public interface ReviewService{

    List<Review> fetchBookReviewsById(Long reviewId);
    public Review saveReview(Long bookId, ReviewDTO reviewDTO) throws BookNotFoundException;
    public void deleteReviewById(Long reviewId);
}
