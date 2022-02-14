package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("books/{book_id}/reviews")
    public List<Review> getBookReviews(@PathVariable("book_id") Long bookId){
        return reviewService.findAllByBookId(bookId);
    }

    @PostMapping("books/{book_id}/reviews")
    public Review saveBookReview(@PathVariable("book_id") Long bookId,
                                 @RequestBody ReviewDTO reviewDTO) throws BookNotFoundException {
        return reviewService.saveReviewToBook(bookId, reviewDTO);
    }

    @PutMapping("reviews/{review_id}")
    public Review updateBookReview(@PathVariable("review_id") Long reviewId,
                                 @RequestBody ReviewDTO reviewDTO)
        throws ReviewNotFoundException {
        return reviewService.updateReview(reviewId, reviewDTO);
    }

    @DeleteMapping("reviews/{review_id}")
    public String deleteBookReview(@PathVariable("review_id") Long reviewId) {
        reviewService.deleteById(reviewId);
        return "Review deleted Successfully";
    }
}
