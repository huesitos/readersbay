package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuoteControllerUnitTest {

  @Test
  void fetchBookQuotes() {
    QuoteService service = Mockito.mock(QuoteService.class);
    when(service.getQuotesByBookId(1L)).thenReturn(new ArrayList<>());
    QuoteController quoteController = new QuoteController(service);
    assertEquals(0, quoteController.fetchBookQuotes(1L).size());
  }
}