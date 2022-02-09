package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;

import java.util.List;

public interface QuoteService {
  List<Quote> getQuotesByBookId(Long bookId);
  Quote saveQuoteToBook(Long bookId, QuoteDTO quoteDTO) throws BookNotFoundException;
}
