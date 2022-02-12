package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
