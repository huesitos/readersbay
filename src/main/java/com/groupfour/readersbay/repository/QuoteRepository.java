package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  @Query(
      value = "select * from quotes q where q.book_id = ?1",
      nativeQuery = true
  )
  List<Quote> findByBookId(Long bookId);
}
