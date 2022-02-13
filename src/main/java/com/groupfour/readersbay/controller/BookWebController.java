package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.*;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
import com.groupfour.readersbay.service.QuoteService;
import com.groupfour.readersbay.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String fetchBooks(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("book",bookDTO);
        return "books";
    }

    @GetMapping("/{book_id}")
    public String getBook(@PathVariable("book_id") Long bookId, Model model)
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
    public String saveBook(@ModelAttribute("bookCrt") BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
        return "redirect:books";
    }
}
