package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.helpers.LibrePDFExporter;
import com.groupfour.readersbay.helpers.PDFExporter;
import com.groupfour.readersbay.service.BookService;
import com.groupfour.readersbay.service.ReflectionService;
import com.lowagie.text.DocumentException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/books")
public class BookWebController {
    private BookService bookService;
    private ReflectionService reflectionService;

    public BookWebController (BookService bookService,ReflectionService reflectionService) {
        super();
        this.bookService = bookService;
        this.reflectionService = reflectionService;
    }

    @GetMapping()
    public String fetchBooks(@NotNull Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }

    @GetMapping("/{book_id}")
    public String getBook(@PathVariable("book_id") Long bookId, @NotNull Model model)
            throws BookNotFoundException {
        ReviewDTO reviewDTO = new ReviewDTO();
        ReflectionDTO reflectionDTO = new ReflectionDTO();
        model.addAttribute("modelBookId",bookId);
        model.addAttribute("review",reviewDTO);
        model.addAttribute("reflection",reflectionDTO);
        model.addAttribute("book",bookService.getBook(bookId));
        model.addAttribute("reflections", reflectionService.getReflections(bookId));
        return "book";
    }

    @GetMapping("/export/pdf/{book_id}")
    public void exportToPDF(@PathVariable("book_id") Long bookId,
                            @NotNull HttpServletResponse response)
        throws DocumentException, BookNotFoundException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Book book = bookService.getBook(bookId);

        PDFExporter exporter = new LibrePDFExporter();
        exporter.exportBook(response, book);
    }
}
