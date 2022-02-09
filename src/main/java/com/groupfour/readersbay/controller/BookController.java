package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
@Log4j2
public class BookController {
  @Autowired
  BookService bookService;

  @GetMapping
  public List<Book> fetchBooks() {
    log.info("BookController: /books fetchBooks");
    return bookService.getBooks();
  }

  @PostMapping
  public Book saveBook(@RequestBody BookDTO bookDTO) {
    log.info("BookController: /books saveBooks");
    return bookService.saveBook(bookDTO);
  }

  @PutMapping("/{book_id}")
  public Book updateBook(@PathVariable("book_id") Long bookId,
                         @RequestBody BookDTO bookDTO) throws BookNotFoundException {
    log.info("BookController: /books/{book_id} updateBook");
    return bookService.updateBook(bookId, bookDTO);
  }

  @DeleteMapping("/{book_id}")
  public String deleteBook(@PathVariable("book_id") Long bookId) throws BookNotFoundException {
    log.info("BookController: /books/{book_id} deleteBook");
    return bookService.deleteBook(bookId);
  }
}
