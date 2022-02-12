package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
import com.groupfour.readersbay.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String fetchBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }

    @GetMapping("/{book_id}")
    public String getBook(@PathVariable("book_id") Long bookId, Model model)
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
}
