package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class QuoteServiceImpl implements QuoteService {

  @Autowired
  QuoteRepository quoteRepository;

  @Override
  public List<Quote> getQuotesByBookId(Long id) {
    log.info("Fetching all quotes with book_id {}", id);
    return quoteRepository.findByBookId(id);
  }
}
