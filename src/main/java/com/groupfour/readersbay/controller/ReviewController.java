package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "publications/")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("{book_param}/reviews")
    public List<Review> getBookReviews(@PathVariable("book_param") Long reviewId){
        return reviewService.fetchReviewById(reviewId);
    }

    @PostMapping("{book_param}/reviews")
    public Review saveBookReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }

    @DeleteMapping("{book_param}/reviews/{review_param}")
    public String deleteBookReview(@PathVariable("review_param") Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return "Review deleted Successfully";
    }
}
