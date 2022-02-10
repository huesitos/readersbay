package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.QuoteNotFoundException;

import java.util.List;

public interface QuoteService {
  List<Quote> getQuotesByBookId(Long bookId);

  Quote saveQuoteToBook(Long bookId, QuoteDTO quoteDTO) throws BookNotFoundException;

  Quote updateQuote(Long quoteId, QuoteDTO quoteDTO) throws QuoteNotFoundException;

  void deleteQuote(Long quoteId) throws QuoteNotFoundException;
}
