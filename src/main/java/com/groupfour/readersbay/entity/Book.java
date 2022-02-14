package com.groupfour.readersbay.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
  @Column(columnDefinition="TEXT")
  private String description;
  @Column(columnDefinition="TEXT")
  private String motivation;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "book"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Quote> quotes;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "book"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Review> reviews;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "book"
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Reflection> reflections;

  private String userId;
}
