package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.repository.ReviewRepository;
import com.groupfour.readersbay.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewControllerUnitTest {

    @Test
    void getBookReviews(){
        ReviewService reviewService = Mockito.mock(ReviewService.class);
        when(reviewService.fetchBookReviewById(1L)).thenReturn(new ArrayList<>());
        ReviewController reviewController = new ReviewController(reviewService);
        assertEquals(0, reviewController.getBookReviews(1L).size());
    }
}