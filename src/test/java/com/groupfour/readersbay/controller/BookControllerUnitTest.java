package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.entity.ReadingStatus;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookControllerUnitTest {

  private Book book;
  private BookDTO bookDTO;

  @BeforeEach
  void setUp() {
    book = Book.builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    bookDTO = new BookDTO(
        "New Title",
        "Author",
        "Description",
        "Motivation",
        ReadingStatus.READING,
        "",
        "");
  }

  @Test
  void fetchBooks() {
    BookService bookService = Mockito.mock(BookService.class);
    BookController bookController = new BookController(bookService);
    when(bookService.getBooks()).thenReturn(List.of(book));
    assertEquals(1, bookController.fetchBooks().size());
  }

  @Test
  void getBook() throws BookNotFoundException {
    BookService bookService = Mockito.mock(BookService.class);
    BookController bookController = new BookController(bookService);
    when(bookService.getBook(1L)).thenReturn(book);
    assertEquals("Title", bookController.getBook(1L).getTitle());
  }

  @Test
  void saveBook() {
    BookService bookService = Mockito.mock(BookService.class);
    BookController bookController = new BookController(bookService);

    String title = "Title";
    when(bookService.saveBook(bookDTO)).thenReturn(book);
    assertEquals(title, bookController.saveBook(bookDTO).getTitle());
  }

  @Test
  void updateBook() throws BookNotFoundException {
    BookService bookService = Mockito.mock(BookService.class);
    BookController bookController = new BookController(bookService);

    String newTitle = "Book";
    book.setTitle(newTitle);
    when(bookService.updateBook(1L, bookDTO)).thenReturn(book);
    assertEquals(newTitle, bookController.updateBook(1L, bookDTO).getTitle());
  }

  @Test
  void deleteBook() throws BookNotFoundException {
    BookService bookService = Mockito.mock(BookService.class);
    BookController bookController = new BookController(bookService);

    String message = String.format("Book %d deleted", 1L);
    when(bookService.deleteBook(1L)).thenReturn(message);
    assertEquals(message, bookController.deleteBook(1L));
  }
}