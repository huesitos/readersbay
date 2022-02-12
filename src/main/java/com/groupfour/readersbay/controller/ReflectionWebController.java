package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.entity.Visibility;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.service.ReflectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "books/")
public class ReflectionWebController {
    private ReflectionService reflectionService;
    public ReflectionWebController(ReflectionService reflectionService) {
        super();
        this.reflectionService = reflectionService;
    }

    @PostMapping("{book_id}/reflections")
    public String saveReflection(@PathVariable("book_id") Long bookId,
                                 @ModelAttribute("reviewObj") ReflectionDTO reflectionDTO) throws BookNotFoundException {
        ReflectionDTO reflectionDTO1 = new ReflectionDTO();
        reflectionDTO1.setContent(reflectionDTO.getContent());
        reflectionDTO1.setVisibility(reflectionDTO.getVisibility());
        reflectionService.saveReflection(bookId, reflectionDTO1);
        return "redirect:/books";
    }
}
