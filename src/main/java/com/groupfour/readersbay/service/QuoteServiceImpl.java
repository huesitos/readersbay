package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.QuoteNotFoundException;
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
  BookService bookService;

  @Override
  public List<Quote> getQuotesByBookId(Long bookId) {
    log.info("Fetching all quotes with book_id {}", bookId);
    return quoteRepository.findByBookId(bookId);
  }

  @Override
  public Quote saveQuoteToBook(Long bookId, @NotNull QuoteDTO quoteDTO)
      throws BookNotFoundException {
    log.info("saving quote to book {}", bookId);
    Book book = bookService.getBook(bookId);

    Quote quote = Quote
        .builder()
        .content(quoteDTO.getContent())
        .visibility(quoteDTO.getVisibility())
        .creationDate(LocalDate.now())
        .book(book)
        .build();
    return quoteRepository.save(quote);
  }

  @Override
  public Quote updateQuote(Long quoteId, QuoteDTO quoteDTO) throws QuoteNotFoundException {
    log.info("Updating quote {} with {}", quoteId, quoteDTO);
    Quote quote = findQuoteById(quoteId);

    if (quoteDTO.getContent() != null && !quoteDTO.getContent().isEmpty()) {
      quote.setContent(quoteDTO.getContent());
    }

    if (quoteDTO.getVisibility() != null) {
      quote.setVisibility(quoteDTO.getVisibility());
    }

    return quoteRepository.save(quote);
  }

  @Override
  public void deleteQuote(Long quoteId) throws QuoteNotFoundException {
    log.info("Deleting quote {}", quoteId);
    quoteRepository.delete(findQuoteById(quoteId));
  }

  private @NotNull Quote findQuoteById(Long quoteId) throws QuoteNotFoundException {
    Optional<Quote> optionalQuote = quoteRepository.findById(quoteId);

    if (optionalQuote.isEmpty()) {
      String message = String.format("Quote with id %d not found", quoteId);
      log.error(message);
      throw new QuoteNotFoundException(message);
    }

    return optionalQuote.get();
  }
}
