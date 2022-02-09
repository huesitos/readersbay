package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import com.groupfour.readersbay.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class QuoteServiceImpl implements QuoteService {

  @Autowired
  QuoteRepository quoteRepository;
  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Quote> getQuotesByBookId(Long bookId) {
    log.info("Fetching all quotes with book_id {}", bookId);
    return quoteRepository.findByBookId(bookId);
  }

  @Override
  public Quote saveQuoteToBook(Long bookId, @NotNull QuoteDTO quoteDTO)
      throws BookNotFoundException {
    log.info("Fetching all quotes with book_id {}", bookId);
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isEmpty()) {
      throw new BookNotFoundException(
          String.format("Book with id %d does not exist", bookId));
    }

    Quote quote = Quote
        .builder()
        .content(quoteDTO.getContent())
        .visibility(quoteDTO.getVisibility())
        .creationDate(LocalDate.now())
        .book(optionalBook.get())
        .build();
    return quoteRepository.save(quote);
  }
}
