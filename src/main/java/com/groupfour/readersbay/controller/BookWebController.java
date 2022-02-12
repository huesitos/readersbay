package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.BookService;
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

    public BookWebController (BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @GetMapping()
    public String fetchBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }

    @GetMapping("/{book_id}")
    public String getBook(@PathVariable("book_id") Long bookId, Model model)
            throws BookNotFoundException {
        model.addAttribute("book",bookService.getBook(bookId));
        return "book";
    }
}
