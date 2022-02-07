package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServicesImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review fetchReviewById(Long reviewId) throws ReviewNotFoundException {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(!review.isPresent()) {
            throw new ReviewNotFoundException("Review Not Available");
        }
        return review.get();
    }
}
