package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Book> getBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book saveBook(@NotNull BookDTO bookDTO) {
    Book book = Book
        .builder()
        .author(bookDTO.getAuthor())
        .title(bookDTO.getTitle())
        .description(bookDTO.getDescription())
        .motivation(bookDTO.getMotivation())
        .build();

    return bookRepository.save(book);
  }

  @Override
  public Book updateBook(Long bookId, @NotNull BookDTO bookDTO) throws BookNotFoundException {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isEmpty()) {
      throw new BookNotFoundException(String.format("Book with id %d not found", bookId));
    }

    Book book = optionalBook.get();
    if (bookDTO.getTitle() != null && !bookDTO.getTitle().isEmpty()) {
      book.setTitle(bookDTO.getTitle());
    }

    if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().isEmpty()) {
      book.setAuthor(bookDTO.getAuthor());
    }

    if (bookDTO.getDescription() != null && !bookDTO.getDescription().isEmpty()) {
      book.setDescription(bookDTO.getDescription());
    }

    if (bookDTO.getMotivation() != null && !bookDTO.getMotivation().isEmpty()) {
      book.setMotivation(bookDTO.getMotivation());
    }

    return bookRepository.save(book);
  }

  @Override
  public String deleteBook(Long bookId) throws BookNotFoundException {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isEmpty()) {
      throw new BookNotFoundException(String.format("Book with id %d not found", bookId));
    }

    bookRepository.delete(optionalBook.get());

    return String.format("Book %d deleted", bookId);
  }
}
