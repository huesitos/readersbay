package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.QuoteNotFoundException;
import com.groupfour.readersbay.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/")
public class QuoteController {

  @Autowired
  QuoteService quoteService;

  @GetMapping("books/{book_id}/quotes")
  public List<Quote> fetchBookQuotes(@PathVariable("book_id") Long bookId) {
    log.info("QuoteController: /{book_id}/quotes fetchBookQuotes with id {}", bookId);
    return quoteService.getQuotesByBookId(bookId);
  }

  @PostMapping("books/{book_id}/quotes")
  public Quote saveBookQuote(@PathVariable("book_id") Long bookId,
                                   @RequestBody QuoteDTO quoteDTO) throws BookNotFoundException {
    log.info("QuoteController: /{book_id}/quotes saveBookQuote {} to book id {}",
        quoteDTO, bookId);
    return quoteService.saveQuoteToBook(bookId, quoteDTO);
  }

  @PutMapping("/quotes/{quote_id}")
  public Quote updateBookQuote(@PathVariable("quote_id") Long quoteId,
                             @RequestBody QuoteDTO quoteDTO) throws QuoteNotFoundException {
    log.info("QuoteController: /quotes/{quote_id} updateBookQuote {} with {}",
        quoteId, quoteDTO);
    return quoteService.updateQuote(quoteId, quoteDTO);
  }

  @DeleteMapping("/quotes/{quote_id}")
  public String deleteBookQuote(@PathVariable("quote_id") Long quoteId)
      throws QuoteNotFoundException {
    log.info("QuoteController: /quotes/{quote_id} deleteBookQuote {}", quoteId);
    quoteService.deleteQuote(quoteId);
    return "Quote deleted successfully.";
  }
}
