package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.ReviewService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String reviewsBook(@PathVariable("book_param") Long bookId, @NotNull Model model) {
        model.addAttribute("reviews",reviewService.findAllByBookId(bookId));
        return "reviews";
    }

    @PostMapping("{book_id}/reviews")
    public String saveBookReview(@PathVariable("book_id") Long bookId,
                                 @ModelAttribute("reviewObj") @NotNull ReviewDTO reviewDTO)
        throws BookNotFoundException {
        reviewService.saveReviewToBook(bookId, reviewDTO);
        return "redirect:/books";
    }
}
