package com.groupfour.readersbay;

import com.groupfour.readersbay.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
@SpringBootApplication
public class ReadersBayApplication {
	private BookService bookService;
	public ReadersBayApplication(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	public static void main(String[] args) {
		SpringApplication.run(ReadersBayApplication.class, args);
	}

	@GetMapping
	public String getIndex(Model model){
		model.addAttribute("books", bookService.getBooks());
		return "books";
	}
}
