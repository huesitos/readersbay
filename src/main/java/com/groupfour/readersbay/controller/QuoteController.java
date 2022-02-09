package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/books/")
public class QuoteController {

  @Autowired
  QuoteService quoteService;

  @GetMapping("{book_id}/quotes")
  public List<Quote> fetchBookQuotes(@PathVariable("book_id") Long bookId) {
    log.info("QuoteController: /{book_id}/quotes fetchBookQuotes with id {}", bookId);
    return quoteService.getQuotesByBookId(bookId);
  }

  @PostMapping("{book_id}/quotes")
  public Quote saveBookQuote(@PathVariable("book_id") Long bookId,
                                   @RequestBody QuoteDTO quoteDTO) throws BookNotFoundException {
    log.info("QuoteController: /{book_id}/quotes saveBookQuote {} to book id {}",
        quoteDTO, bookId);
    return quoteService.saveQuoteToBook(bookId, quoteDTO);
  }
}
