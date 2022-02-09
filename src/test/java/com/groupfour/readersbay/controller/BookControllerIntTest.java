package com.groupfour.readersbay.controller;

import com.google.gson.Gson;
import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerIntTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  private Book book;
  private BookDTO bookDTO;

  @BeforeEach
  void setUp() {
    book = Book.builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    bookDTO = new BookDTO("Title", "Author", null, null);
  }

  @Test
  @DisplayName("Fetch all books")
  void fetchBooks() {
    when(bookService.getBooks()).thenReturn(List.of(book, book, book));

    try {
      MvcResult mvcResult = mockMvc.perform(get("/books")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk()).andReturn();

      assertEquals(3,
          new Gson()
              .fromJson(mvcResult.getResponse().getContentAsString(), JSONArray.class)
              .size());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  @DisplayName("Save a book")
  void saveBook() {
    when(bookService.saveBook(bookDTO)).thenReturn(book);

    try {
      mockMvc.perform(post("/books")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new Gson().toJson(bookDTO, BookDTO.class)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.title").value(book.getTitle()));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  @DisplayName("Update an existing book")
  void updateBook() throws BookNotFoundException {
    when(bookService.updateBook(1L, bookDTO)).thenReturn(book);

    try {
      mockMvc.perform(put("/books/1")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new Gson().toJson(bookDTO, BookDTO.class)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.title").value(book.getTitle()));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  @DisplayName("Update a non-existing book")
  void updateNonExistingBook() throws BookNotFoundException {
    when(bookService.updateBook(1L, bookDTO)).thenThrow(BookNotFoundException.class);

    try {
      assertThrows(NestedServletException.class, ()->
          mockMvc.perform(put("/books/1")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new Gson().toJson(bookDTO, BookDTO.class)))
      );
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  @DisplayName("Delete an existing book")
  void deleteBook() throws BookNotFoundException {
    when(bookService.deleteBook(1L)).thenReturn(anyString());

    try {
      mockMvc.perform(delete("/books/1")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  @DisplayName("Delete a non-existing book")
  void deleteNonExistingBook() throws BookNotFoundException {
    when(bookService.deleteBook(1L)).thenThrow(BookNotFoundException.class);

    try {
      assertThrows(NestedServletException.class, ()->
      mockMvc.perform(delete("/books/1")
              .contentType(MediaType.APPLICATION_JSON))
      );
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}