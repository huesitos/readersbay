package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Review;
import com.groupfour.readersbay.entity.ReviewDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReviewNotFoundException;

import java.util.List;

public interface ReviewService{

  List<Review> findAllByBookId(Long bookId);
  Review saveReviewToBook(Long bookId, ReviewDTO reviewDTO) throws BookNotFoundException;
  void deleteById(Long bookId);
  Review updateReview(Long reviewId, ReviewDTO reviewDTO) throws ReviewNotFoundException;
}
