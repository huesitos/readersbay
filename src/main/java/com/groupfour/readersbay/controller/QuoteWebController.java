package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.QuoteDTO;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.QuoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "books/")
public class QuoteWebController {
    private QuoteService quoteService;
    public QuoteWebController (QuoteService quoteService) {
        super();
        this.quoteService = quoteService;
    }
    @PostMapping("{book_id}/quotes")
    public String saveReflection(@PathVariable("book_id") Long bookId,
                                 @ModelAttribute("quoteObj")QuoteDTO quoteDTO) throws BookNotFoundException {
        quoteService.saveQuoteToBook(bookId,quoteDTO);
        return "redirect:/books/"+bookId+"#nav-quotes";
    }
}
