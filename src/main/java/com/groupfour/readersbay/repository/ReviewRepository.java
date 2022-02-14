package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository
        extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM reviews as r WHERE r.book_id = ?1", nativeQuery = true)
    public List<Review> findAllByBookId(Long bookId);
}
