package com.groupfour.readersbay.helpers;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Quote;
import com.groupfour.readersbay.entity.Reflection;
import com.groupfour.readersbay.entity.Review;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LibrePDFExporter implements PDFExporter {

  @Override
  public void exportBook(@NotNull HttpServletResponse response, Book book)
      throws DocumentException {
    try (Document document = new Document(PageSize.A4)) {
      PdfWriter.getInstance(document, response.getOutputStream());

      document.open();

      Font headingFont = new Font(Font.HELVETICA, 12, Font.BOLD);

      writeBookDetails(document, headingFont, book);
      writeReviews(document, headingFont, book.getReviews());
      writeQuotes(document, headingFont, book.getQuotes());
      writeReflections(document, headingFont, book.getReflections());

    } catch (DocumentException | IOException documentException) {
      log.error(documentException.getStackTrace());
      throw new DocumentException("Could not export the document.");
    }
  }

  private void writeBookDetails(@NotNull Document document,
                                Font headingFont,
                                @NotNull Book book) {
    document.add(new Paragraph("Title: " + book.getTitle(), headingFont));
    document.add(new Paragraph("Author: " + book.getAuthor(), headingFont));
    document.add(new Paragraph("Description: " + book.getDescription(), headingFont));
    document.add(new Paragraph("Genre: " + book.getGenre(), headingFont));
    document.add(new Paragraph("Subject: " + book.getSubject(), headingFont));
  }

  private void writeReviews(@NotNull Document document,
                           Font headingFont,
                           java.util.@NotNull List<Review> reviews) {
    document.add(new Paragraph("Reviews", headingFont));

    for (Review review: reviews) {
      document.add(new Paragraph("Score: " + review.getScore(), headingFont));
      document.add(new Paragraph("Review: " + review.getText(), headingFont));
    }
  }

  private void writeQuotes(@NotNull Document document,
                           Font headingFont,
                           @NotNull java.util.List<Quote> quotes) {
    document.add(new Paragraph("Quotes", headingFont));
    com.lowagie.text.List list =
        new com.lowagie.text.List(com.lowagie.text.List.UNORDERED, 14);
    list.setIndentationLeft(20);

    for (Quote quote: quotes) {
      list.add(new ListItem(quote.getContent()));
    }
    document.add(list);
  }

  private void writeReflections(@NotNull Document document,
                                Font headingFont,
                                @NotNull java.util.List<Reflection> reflections) {
    for (Reflection reflection : reflections) {
      document.add(new Paragraph("Title" + reflection.getTitle(), headingFont));
      document.add(new Paragraph(reflection.getContent(), headingFont));
    }
  }
}
