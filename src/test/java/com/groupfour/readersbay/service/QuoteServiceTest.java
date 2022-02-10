package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.QuoteNotFoundException;
import com.groupfour.readersbay.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class QuoteServiceTest {

  private QuoteRepository quoteRepository;
  private BookService bookService;
  private Book book;
  private Quote quote;

  @BeforeEach
  void setUp() {
    quoteRepository = Mockito.mock(QuoteRepository.class);
    bookService = Mockito.mock(BookService.class);

    book = Book.builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    quote = Quote.builder()
        .quoteId(1L)
        .content("Content")
        .visibility(Visibility.PRIVATE)
        .book(book)
        .creationDate(LocalDate.now())
        .build();

    when(quoteRepository.save(any()))
        .thenReturn(quote);
  }

  @Test
  @DisplayName("Get all quotes from a book")
  void whenExistingBookId_thenQuotesFound() {
    when(quoteRepository.findByBookId(1L))
        .thenReturn(List.of(
            Quote.builder()
                .quoteId(1L)
                .content("Hello")
                .visibility(Visibility.PRIVATE)
                .creationDate(LocalDate.now())
                .build()
        ));
    QuoteService quoteService =
        new QuoteServiceImpl(quoteRepository, bookService);
    List<Quote> quoteList = quoteService.getQuotesByBookId(1L);
    assertEquals(1, quoteList.size());
  }

  @Test
  @DisplayName("Save a quote from an existing book")
  void whenExistingBookId_thenQuoteSaved() throws BookNotFoundException {
    when(bookService.getBook(1L))
        .thenReturn(book);

    QuoteDTO quoteDTO = Mockito.mock(QuoteDTO.class);
    when(quoteDTO.getContent()).thenReturn("Content");
    when(quoteDTO.getVisibility()).thenReturn(Visibility.PRIVATE);

    QuoteService quoteService =
        new QuoteServiceImpl(quoteRepository, bookService);
    Quote savedQuote = quoteService.saveQuoteToBook(1L, quoteDTO);
    assertEquals("Content", savedQuote.getContent());
  }

  @Test
  @DisplayName("Save a quote from a non existing book")
  void whenNotExistingBookId_thenQuoteNotSaved() throws BookNotFoundException {
    when(bookService.getBook(1L))
        .thenThrow(BookNotFoundException.class);

    QuoteDTO quoteDTO = Mockito.mock(QuoteDTO.class);
    when(quoteDTO.getContent()).thenReturn("Content");
    when(quoteDTO.getVisibility()).thenReturn(Visibility.PRIVATE);

    QuoteService quoteService =
        new QuoteServiceImpl(quoteRepository, bookService);
    assertThrows(BookNotFoundException.class, () ->
        quoteService.saveQuoteToBook(1L, quoteDTO));
  }

  @Test
  @DisplayName("Update a quote")
  void whenQuoteExists_thenQuoteUpdated() throws QuoteNotFoundException {

    when(quoteRepository.findById(1L))
        .thenReturn(Optional.of(quote));

    when(quoteRepository.save(any())).thenReturn(quote);

    QuoteDTO quoteDTO = Mockito.mock(QuoteDTO.class);
    when(quoteDTO.getContent()).thenReturn("New Content");
    when(quoteDTO.getVisibility()).thenReturn(Visibility.PUBLIC);

    QuoteService quoteService =
        new QuoteServiceImpl(quoteRepository, bookService);
    Quote savedQuote = quoteService.updateQuote(1L, quoteDTO);
    assertEquals("New Content", savedQuote.getContent());
  }

  @Test
  @DisplayName("Delete a quote")
  void whenQuote_thenDelete() {
    when(quoteRepository.findById(1L))
        .thenReturn(Optional.of(quote));

    assertDoesNotThrow(() -> {
      QuoteService quoteService =
          new QuoteServiceImpl(quoteRepository, bookService);
      quoteService.deleteQuote(1L);
    });
  }
}