package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServicesImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> fetchReviewById(Long reviewId){
        List<Review> review = reviewRepository.fetchReviewById(reviewId);
        return review;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
