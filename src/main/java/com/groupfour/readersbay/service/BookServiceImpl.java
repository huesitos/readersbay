package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.BookDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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
    log.info("BookService: Saving book {}", bookDTO);
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
  public Book getBook(Long bookId) throws BookNotFoundException {
    log.info("BookService: Getting book {}", bookId);
    return findBookById(bookId);
  }

  @Override
  public Book updateBook(Long bookId, @NotNull BookDTO bookDTO) throws BookNotFoundException {
    log.info("BookService: Updating book {}", bookId);

    Book book = findBookById(bookId);
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
    log.info("BookService: Deleting book {}", bookId);

    bookRepository.delete(findBookById(bookId));

    return String.format("Book with id %d deleted", bookId);
  }

  private @NotNull Book findBookById(Long bookId) throws BookNotFoundException {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isEmpty()) {
      String message = String.format("Book with id %d not found", bookId);
      log.error(message);
      throw new BookNotFoundException(message);
    }

    return optionalBook.get();
  }
}
