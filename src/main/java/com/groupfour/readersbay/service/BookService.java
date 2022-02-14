package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.entity.ReadingStatus;
import com.groupfour.readersbay.exception.BookNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BookService {
  List<Book> getBooks();

  Book saveBook(BookDTO bookDTO);

  Book updateBook(Long bookId, @NotNull BookDTO bookDTO) throws BookNotFoundException;

  String deleteBook(Long bookId) throws BookNotFoundException;

  Book getBook(Long bookId) throws BookNotFoundException;

  List<Book> getBooksStatus(int status);
}
