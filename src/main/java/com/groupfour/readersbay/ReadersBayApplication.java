package com.groupfour.readersbay;

import com.groupfour.readersbay.entity.Review;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication

public class ReadersBayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadersBayApplication.class, args);
	}


}
