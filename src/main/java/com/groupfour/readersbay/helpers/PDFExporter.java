package com.groupfour.readersbay.helpers;

import com.groupfour.readersbay.entity.Book;
import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;

public interface PDFExporter {
  void exportBook(HttpServletResponse response, Book book) throws DocumentException;
}
