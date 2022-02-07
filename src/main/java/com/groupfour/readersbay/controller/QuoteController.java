package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.service.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/publications")
public class QuoteController {

  @Autowired
  QuoteService quoteService;

  @GetMapping("/{book_id}/quotes")
  public List<Quote> fetchAllQuotes(@PathVariable("book_id") Long id) {
    return quoteService.getAllQuotes(id);
  }
}
