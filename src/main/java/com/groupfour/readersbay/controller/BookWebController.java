package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "books/")
public class BookWebController {
    private BookService bookService;

    public BookWebController (BookService bookService) {
        super();
        this.bookService = bookService;
    }
    @GetMapping("index")
    public String fetchBooks(Model model) {
        model.addAttribute("books",bookService.getBooks());
        return "books";
    }
}
