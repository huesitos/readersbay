package com.groupfour.readersbay.controller;

import com.google.gson.Gson;
import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.QuoteNotFoundException;
import com.groupfour.readersbay.service.QuoteService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuoteController.class)
class QuoteControllerIntTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QuoteService quoteService;

  private Quote quote;
  private QuoteDTO quoteDTO;

  @BeforeEach
  void setUp() {
    Book book = Book
        .builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    quote = Quote
        .builder()
        .quoteId(1L)
        .creationDate(LocalDate.now())
        .visibility(Visibility.PRIVATE)
        .content("Hello")
        .book(book)
        .build();

    quoteDTO = new QuoteDTO("Hello", Visibility.PRIVATE);
  }

  @Test
  void fetchBookQuotes() {
    when(quoteService.getQuotesByBookId(1L)).thenReturn(List.of(quote, quote, quote));

    try {
      MvcResult mvcResult = mockMvc.perform(get("/quotes/book/1")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].content").value(quote.getContent())).andReturn();

      assertEquals(3,
          new Gson()
              .fromJson(mvcResult.getResponse().getContentAsString(), JSONArray.class)
              .size());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  void saveBookQuotes() throws BookNotFoundException {
    when(quoteService.saveQuoteToBook(1L, quoteDTO)).thenReturn(quote);

    try {
      mockMvc.perform(post("/quotes/book/1")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new Gson().toJson(quoteDTO, QuoteDTO.class)))
          .andExpect(status().isOk());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  void updateBookQuote() throws QuoteNotFoundException {
    when(quoteService.updateQuote(1L, quoteDTO)).thenReturn(quote);

    try {
      mockMvc.perform(post("/quotes/book/1")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new Gson().toJson(quoteDTO, QuoteDTO.class)))
          .andExpect(status().isOk());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}