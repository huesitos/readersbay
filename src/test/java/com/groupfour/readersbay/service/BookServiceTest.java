package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceTest {

  private BookRepository bookRepository;
  private Book book;
  private BookDTO bookDTO;

  @BeforeEach
  void setUp() {
    bookRepository = Mockito.mock(BookRepository.class);
    book = Book.builder()
        .bookId(1L)
        .author("Author")
        .title("Title")
        .build();

    bookDTO = new BookDTO("New Title", "Author", "Description", "Motivation");
  }

  @Test
  void getBooks() {
    when(bookRepository.findAll()).thenReturn(List.of(book, book, book));

    BookService bookService = new BookServiceImpl(bookRepository);
    List<Book> books = bookService.getBooks();
    assertEquals(3, books.size());
  }

  @Test
  void saveBook() {
    when(bookRepository.save(any())).thenReturn(book);

    BookService bookService = new BookServiceImpl(bookRepository);
    Book book = bookService.saveBook(new BookDTO());
    assertEquals("Title", book.getTitle());
  }

  @Test
  void updateBook() throws BookNotFoundException {
    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(bookRepository.save(any())).thenReturn(book);

    BookService bookService = new BookServiceImpl(bookRepository);
    Book book = bookService.updateBook(1L, bookDTO);
    assertEquals("New Title", book.getTitle());
  }

  @Test
  void updateNonExistingBook() {
    when(bookRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(BookNotFoundException.class, () -> {
      BookService bookService = new BookServiceImpl(bookRepository);
      bookService.updateBook(1L, bookDTO);
    });
  }

  @Test
  void deleteBook() throws BookNotFoundException {
    when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    when(bookRepository.save(any())).thenReturn(book);

    BookService bookService = new BookServiceImpl(bookRepository);
    String string = bookService.deleteBook(1L);
    assertEquals(String.format("Book with id %d deleted", 1L), string);
  }

  @Test
  void deleteNonExistingBook() {
    when(bookRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(BookNotFoundException.class, () -> {
      BookService bookService = new BookServiceImpl(bookRepository);
      bookService.deleteBook(1L);
    });
  }
}