package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.Book;
import com.groupfour.readersbay.entity.Reflection;
import com.groupfour.readersbay.entity.ReflectionDTO;
import com.groupfour.readersbay.exception.BookNotFoundException;
import com.groupfour.readersbay.exception.ReflectionNotFoundException;
import com.groupfour.readersbay.repository.ReflectionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class ReflectionServiceImpl implements ReflectionService {

  @Autowired
  ReflectionRepository reflectionRepository;

  @Autowired
  BookService bookService;

  @Override
  public List<Reflection> getReflections(Long bookId) {
    return reflectionRepository.findAll();
  }

  @Override
  public Reflection saveReflection(Long bookId, @NotNull ReflectionDTO reflectionDTO)
      throws BookNotFoundException {
    Book book = bookService.getBook(bookId);

    Reflection reflection =
        Reflection.builder()
            .title(reflectionDTO.getTitle())
            .content(reflectionDTO.getContent())
            .visibility(reflectionDTO.getVisibility())
            .creationDate(LocalDate.now())
            .book(book)
            .build();

    return reflectionRepository.save(reflection);
  }

  @Override
  public Reflection updateReflection(Long reflectionId, @NotNull ReflectionDTO reflectionDTO)
      throws ReflectionNotFoundException {
    Reflection reflection = findReflectionById(reflectionId);

    if (reflectionDTO.getTitle() != null && !reflectionDTO.getTitle().isEmpty()) {
      reflection.setTitle(reflectionDTO.getTitle());
    }

    if (reflectionDTO.getContent() != null && !reflectionDTO.getContent().isEmpty()) {
      reflection.setContent(reflectionDTO.getContent());
    }

    if (reflectionDTO.getVisibility() != null) {
      reflection.setVisibility(reflectionDTO.getVisibility());
    }

    return reflectionRepository.save(reflection);
  }

  @Override
  public void deleteReflection(Long reflectionId)
      throws ReflectionNotFoundException {
    reflectionRepository.delete(findReflectionById(reflectionId));
  }

  private @NotNull Reflection findReflectionById(Long id) throws ReflectionNotFoundException {
    Optional<Reflection> optionalReflection = reflectionRepository.findById(id);

    if (optionalReflection.isEmpty()) {
      String message = String.format("Reflection with id %d not found", id);
      log.error(message);
      throw new ReflectionNotFoundException(message);
    }

    return optionalReflection.get();
  }
}
