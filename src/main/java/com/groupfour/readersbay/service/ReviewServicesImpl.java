package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReviewNotFoundException;
import com.groupfour.readersbay.repository.BookRepository;
import com.groupfour.readersbay.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ReviewServicesImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Review> fetchBookReviewsById(Long bookId) {
        return reviewRepository.fetchBookReviewById(bookId);
    }

    @Override
    public Review saveReview(Long bookId, @NotNull ReviewDTO reviewDTO)
        throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            String message = String.format("Book with id %d doesn't exist", bookId);
            log.error(message);
            throw new BookNotFoundException(message);
        }

        Review review = Review
            .builder()
            .score(reviewDTO.getScore())
            .text(reviewDTO.getText())
            .date(LocalDate.now())
            .build();

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
