package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository
        extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM review as r WHERE r.id_book = ?1",
            nativeQuery = true)
    List<Review> fetchBookReviewById(Long id);

}
