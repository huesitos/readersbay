package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewControllerUnitTest {

    @Test
    void getBookReviews(){
        ReviewService reviewService = Mockito.mock(ReviewService.class);
        when(reviewService.fetchBookReviewsById(1L)).thenReturn(new ArrayList<>());
        ReviewController reviewController = new ReviewController(reviewService);
        assertEquals(0, reviewController.getBookReviews(1L).size());
    }
}