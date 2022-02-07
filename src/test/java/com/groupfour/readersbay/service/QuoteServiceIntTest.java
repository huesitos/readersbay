package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.repository.QuoteRepository;
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
class QuoteServiceIntTest {

  @Autowired
  QuoteService quoteService;

  @MockBean
  QuoteRepository quoteRepository;

  @Test
  @DisplayName("Get all quotes from an existing book")
  void whenExistingBookId_thenQuotesFound() {
    Mockito.when(quoteRepository.findByBookId(1L))
        .thenReturn(List.of(
            Quote.builder()
                .quoteId(1L)
                .content("Hello")
                .visibility(Visibility.PRIVATE)
                .creationDate(LocalDate.now())
                .build(),
            Quote.builder()
                .quoteId(2L)
                .content("World")
                .visibility(Visibility.PRIVATE)
                .creationDate(LocalDate.now())
                .build()
        ));

    List<Quote> quoteList = quoteService.getQuotesByBookId(1L);
    verify(quoteRepository).findByBookId(1L);
    assertEquals(2, quoteList.size());
  }
}