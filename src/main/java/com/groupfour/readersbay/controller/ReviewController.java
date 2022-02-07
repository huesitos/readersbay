package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.repository.ReviewRepository;
import com.groupfour.readersbay.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/publications/")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("{book_param}/reviews")
    public Review getAllReviews(@PathVariable("book_param") Long reviewId) throws ReviewNotFoundException {
        return reviewService.fetchReviewById(reviewId);
    }

    @PostMapping("{book_param}/reviews")
}
