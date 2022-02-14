package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.*;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.helpers.LibrePDFExporter;
import com.groupfour.readersbay.helpers.PDFExporter;
import com.groupfour.readersbay.service.BookService;
import com.groupfour.readersbay.service.QuoteService;
import com.groupfour.readersbay.service.ReflectionService;
import com.lowagie.text.DocumentException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/books")
public class BookWebController {
    private BookService bookService;
    private ReflectionService reflectionService;
    private QuoteService quoteService;

    public BookWebController (BookService bookService,
                              ReflectionService reflectionService,
                              QuoteService quoteService) {
        super();
        this.bookService = bookService;
        this.reflectionService = reflectionService;
        this.quoteService = quoteService;
    }

    @GetMapping()
    public String fetchBooks(@NotNull Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("book",bookDTO);
        model.addAttribute("booksRead",bookService.getBooksStatus(0));
        model.addAttribute("booksReading",bookService.getBooksStatus(1));
        model.addAttribute("booksToRead",bookService.getBooksStatus(2));
        return "books";
    }

    @GetMapping("/{book_id}")
    public String getBook(@PathVariable("book_id") Long bookId, @NotNull Model model)
            throws BookNotFoundException {
        ReviewDTO reviewDTO = new ReviewDTO();
        ReflectionDTO reflectionDTO = new ReflectionDTO();
        QuoteDTO quoteDTO = new QuoteDTO();
        model.addAttribute("modelBookId",bookId);
        model.addAttribute("review",reviewDTO);
        model.addAttribute("reflection",reflectionDTO);
        model.addAttribute("qoute",quoteDTO);
        model.addAttribute("book",bookService.getBook(bookId));
        model.addAttribute("reflections", reflectionService.getReflections(bookId));
        model.addAttribute("quotes", quoteService.getQuotesByBookId(bookId));
        return "book";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return "redirect:books";
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
