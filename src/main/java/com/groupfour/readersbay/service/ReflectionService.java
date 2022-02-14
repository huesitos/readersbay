package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Reflection;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReflectionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReflectionService {
  List<Reflection> getReflections(Long bookId);

  Reflection saveReflection(Long bookId, ReflectionDTO reflectionDTO) throws BookNotFoundException;

  Reflection updateReflection(Long reflectionId, ReflectionDTO reflectionDTO) throws ReflectionNotFoundException;

  void deleteReflection(Long reflectionId) throws ReflectionNotFoundException;
}
