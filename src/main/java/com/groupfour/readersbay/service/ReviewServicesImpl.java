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
public class ReviewServicesImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Review> findAllByBookId(Long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }

    @Override
    public Review saveReviewToBook(Long bookId, @NotNull ReviewDTO reviewDTO)
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
            .book(book.get())
            .build();
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId, @NotNull ReviewDTO reviewDTO)
        throws ReviewNotFoundException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isEmpty()) {
            String message = String.format("Review with id %d not found", reviewId);
            log.error(message);
            throw new ReviewNotFoundException(message);
        }

        Review review = optionalReview.get();
        if (reviewDTO.getScore() != 0) {
            review.setScore(reviewDTO.getScore());
        }

        if (reviewDTO.getText() != null && !reviewDTO.getText().isEmpty()) {
            review.setText(reviewDTO.getText());
        }

        review.setDate(LocalDate.now());

        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long bookId) {
        reviewRepository.deleteById(bookId);
    }
}
