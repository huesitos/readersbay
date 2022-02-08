package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ReviewControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    @Test
    void getBookReviews() {
    }
}