package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteServiceUnitTest {

  @Test
  void whenExistingBookId_thenQuotesFound() {
    QuoteRepository quoteRepository = Mockito.mock(QuoteRepository.class);
    Mockito.when(quoteRepository.findByBookId(1L))
        .thenReturn(List.of(
            Quote.builder()
                .quoteId(1L)
                .content("Hello")
                .visibility(Visibility.PRIVATE)
                .creationDate(LocalDate.now())
                .build()
        ));
    QuoteService quoteService = new QuoteServiceImpl(quoteRepository);
    List<Quote> quoteList = quoteService.getQuotesByBookId(1L);
    assertEquals(1, quoteList.size());
  }
}