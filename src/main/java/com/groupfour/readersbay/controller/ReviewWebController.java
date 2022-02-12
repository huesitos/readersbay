package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "books/")
public class ReviewWebController {

    private ReviewService reviewService;

    public ReviewWebController(ReviewService reviewService) {
        super();
        this.reviewService = reviewService;
    }

    @GetMapping("{book_param}/reviews")
    public String reviewsBook(@PathVariable("book_param") Long bookId, Model model) {
        model.addAttribute("reviews",reviewService.findAllByBookId(bookId));
        return "reviews";
    }

    @PostMapping("{book_id}/reviews")
    public String saveBookReview(@PathVariable("book_id") Long bookId,
                                 @ModelAttribute("reviewObj")ReviewDTO reviewDTO) throws BookNotFoundException {
        ReviewDTO reviewDTO1 = new ReviewDTO();
        reviewDTO1.setDate(LocalDate.now());
        reviewDTO1.setScore(reviewDTO.getScore());
        reviewDTO1.setText(reviewDTO.getText());
        reviewService.saveReviewToBook(bookId, reviewDTO1);
        return "redirect:/books";
    }
}
