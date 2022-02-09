package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.Visibility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuoteRepositoryTest {

  @Autowired
  QuoteRepository quoteRepository;

  @Test
  @DisplayName("Find list of quotes by their book id")
  void findByBookId() {
    Book book = Book
        .builder()
        .title("Book")
        .author("Author")
        .build();

    Quote quote = Quote
        .builder()
        .content("Great code in some book.")
        .creationDate(LocalDate.now())
        .visibility(Visibility.PRIVATE)
        .book(book)
        .build();

    Quote quote2 = Quote
        .builder()
        .content("Great code 2 in some book.")
        .creationDate(LocalDate.now())
        .visibility(Visibility.PRIVATE)
        .book(book)
        .build();

    quoteRepository.save(quote);
    quoteRepository.save(quote2);

    assertEquals(2,
        quoteRepository.findByBookId(book.getBookId()).size());
    assertEquals(quote.getContent(),
        quoteRepository.findByBookId(book.getBookId()).get(0).getContent());
  }
}