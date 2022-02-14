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
  private final Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
  private final Font headingFont = new Font(Font.HELVETICA, 11, Font.BOLDITALIC);
  private final Font paragraphFont = new Font(Font.HELVETICA, 9, Font.NORMAL);

  @Override
  public void exportBook(@NotNull HttpServletResponse response, Book book)
      throws DocumentException {
    try (Document document = new Document(PageSize.A4)) {
      PdfWriter.getInstance(document, response.getOutputStream());

      document.open();

      writeBookDetails(document, book);
      writeReviews(document, book.getReviews());
      writeQuotes(document, book.getQuotes());
      writeReflections(document, book.getReflections());

    } catch (DocumentException | IOException documentException) {
      log.error(documentException.getStackTrace());
      throw new DocumentException("Could not export the document.");
    }
  }

  private void writeBookDetails(@NotNull Document document,
                                @NotNull Book book) {
    document.add(new Paragraph(book.getTitle(), titleFont));
    document.add(new Paragraph("Author:", headingFont));
    document.add(new Paragraph(book.getAuthor(), paragraphFont));
    document.add(new Paragraph("Description:", headingFont));
    document.add(new Paragraph(book.getDescription(), paragraphFont));
    document.add(new Paragraph("Genre:", headingFont));
    document.add(new Paragraph(book.getGenre(), paragraphFont));
    document.add(new Paragraph("Subject:", headingFont));
    document.add(new Paragraph(book.getSubject(), paragraphFont));
    document.add(new Paragraph("\n"));
  }

  private void writeReviews(@NotNull Document document,
                           java.util.@NotNull List<Review> reviews) {
    document.add(new Paragraph("Reviews", titleFont));

    for (Review review: reviews) {
      document.add(new Paragraph("Score: ", headingFont));
      document.add(new Paragraph(String.valueOf(review.getScore()), paragraphFont));
      document.add(new Paragraph("Review: ", headingFont));
      document.add(new Paragraph(review.getText(), paragraphFont));
      document.add(new Paragraph("\n"));
    }
  }

  private void writeQuotes(@NotNull Document document,
                           @NotNull java.util.List<Quote> quotes) {
    document.add(new Paragraph("Quotes", titleFont));

    com.lowagie.text.List list =
        new com.lowagie.text.List(com.lowagie.text.List.UNORDERED, 14);
    list.setIndentationLeft(20);

    for (Quote quote: quotes) {
      list.add(new ListItem(quote.getContent(), paragraphFont));
    }

    document.add(list);
  }

  private void writeReflections(@NotNull Document document,
                                @NotNull java.util.List<Reflection> reflections) {
    document.add(new Paragraph("Reflections", titleFont));

    for (Reflection reflection : reflections) {
      document.add(new Paragraph(reflection.getTitle(), headingFont));
      document.add(new Paragraph(reflection.getContent(), paragraphFont));
      document.add(new Paragraph("\n"));
    }
  }
}
