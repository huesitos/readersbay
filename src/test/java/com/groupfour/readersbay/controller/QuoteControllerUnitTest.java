package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import com.groupfour.readersbay.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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

  @Test
  void saveBookQuotes() throws BookNotFoundException {
    QuoteService service = Mockito.mock(QuoteService.class);
    BookRepository bookRepository = Mockito.mock(BookRepository.class);

    Book book = Book.builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    Quote quote = Quote.builder()
        .quoteId(1L)
        .content("Content")
        .visibility(Visibility.PRIVATE)
        .book(book)
        .creationDate(LocalDate.now())
        .build();

    QuoteDTO quoteDTO = Mockito.mock(QuoteDTO.class);
    when(quoteDTO.getContent()).thenReturn("Content");
    when(quoteDTO.getVisibility()).thenReturn(Visibility.PRIVATE);

    when(service.saveQuoteToBook(1L, quoteDTO)).thenReturn(quote);
    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    QuoteController quoteController = new QuoteController(service);

    assertEquals(quote, quoteController.saveBookQuote(1L, quoteDTO));
  }
}