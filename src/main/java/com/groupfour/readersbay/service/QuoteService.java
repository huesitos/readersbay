package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Quote;

import java.util.List;

public interface QuoteService {
  List<Quote> getAllQuotes(Long id);
}
