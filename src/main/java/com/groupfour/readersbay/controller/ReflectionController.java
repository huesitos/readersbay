package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.Reflection;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReflectionNotFoundException;
import com.groupfour.readersbay.service.ReflectionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/books/")
public class ReflectionController {

  @Autowired
  ReflectionService reflectionService;

  @GetMapping("/{book_id}/reflections")
  public List<Reflection> fetchBookReflections(@PathVariable("book_id") Long bookId) {
    log.info("ReflectionController: /books/{book_id}/reflections fetchReflection");
    return reflectionService.getReflections(bookId);
  }

  @PostMapping("/{book_id}/reflections")
  public Reflection saveReflection(@PathVariable("book_id") Long bookId,
                                   @RequestBody ReflectionDTO reflectionDTO)
      throws BookNotFoundException {
    log.info("ReflectionController: /books/{book_id}/reflections saveReflection");
    return reflectionService.saveReflection(bookId, reflectionDTO);
  }

  @PutMapping("/reflections/{reflection_id}")
  public Reflection updateReflection(@PathVariable("reflection_id") Long reflectionId,
                                     @RequestBody ReflectionDTO reflectionDTO)
      throws ReflectionNotFoundException {
    log.info("ReflectionController: /books/reflections/{reflection_id} updateReflection");
    return reflectionService.updateReflection(reflectionId, reflectionDTO);
  }

  @DeleteMapping("/reflections/{reflection_id}")
  public String deleteReflection(@PathVariable("reflection_id") Long reflectionId)
      throws ReflectionNotFoundException {
    log.info("ReflectionController: /books/reflections/{reflection_id} deleteReflection");
    reflectionService.deleteReflection(reflectionId);
    return "Reflection successfully deleted.";
  }
}
