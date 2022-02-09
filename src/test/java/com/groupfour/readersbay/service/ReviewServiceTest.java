package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @MockBean
    ReviewRepository reviewRepository;


    @Test
    @DisplayName("Get all reviews from an existing book")
    void whenExistingBookId_thenReviewFound() {
        Mockito.when(reviewRepository.fetchBookReviewById(1L))
                .thenReturn(List.of(
                        Review.builder()
                        .id_review(1L)
                        .review("My 1th review")
                        .date(LocalDate.now())
                        .socore(5)
                        .build()
                ));


        List<Review> reviewList = reviewService.fetchBookReviewsById(1L);
        verify(reviewRepository).fetchBookReviewById(1L);
        assertEquals(1, reviewList.size());
    }
}