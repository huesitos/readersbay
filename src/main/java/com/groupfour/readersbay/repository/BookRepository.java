package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books as b WHERE b.status = ?1", nativeQuery = true)
    List<Book> findAllByStatus(int status);
}
