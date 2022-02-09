package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("{book_param}/reviews")
    public List<Review> getBookReviews(@PathVariable("book_param") Long bookId){
        return reviewService.fetchBookReviewsById(bookId);
    }

    @PostMapping("{book_param}/reviews")
    public Review saveBookReview(@PathVariable("book_param") Long bookId,
                                 @RequestBody ReviewDTO reviewDTO) throws BookNotFoundException {
        return reviewService.saveReview(bookId, reviewDTO);
    }

    @DeleteMapping("{book_param}/reviews")
    public String deleteBookReview(@PathVariable("review_param") Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return "Review deleted Successfully";
    }
}
