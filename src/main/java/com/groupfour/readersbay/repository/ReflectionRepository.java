package com.groupfour.readersbay.repository;

import com.groupfour.readersbay.entity.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
}
