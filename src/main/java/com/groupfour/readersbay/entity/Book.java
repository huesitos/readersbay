package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "books")
public class Book {
  @Id
  @SequenceGenerator(
      name = "book_sequence",
      sequenceName = "book_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
  private Long bookId;
  @Column(nullable=false, length=200)
  @NotBlank
  private String title;
  @Column(nullable=false, length=150)
  private String author;
  @Column(nullable = false)
  private ReadingStatus status;
  private String genre;
  private String subject;
  private String description;
  private String motivation;

  @OneToMany(mappedBy = "book")
  private List<Quote> quotes;

  @OneToMany(mappedBy = "book")
  private List<Review> reviews;

  @OneToMany(mappedBy = "book")
  private List<Reflection> reflections;
}
