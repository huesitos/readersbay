package com.groupfour.readersbay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "reflections")
public class Reflection {
  @Id
  @SequenceGenerator(
      name = "reflection_sequence",
      sequenceName = "reflection_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflection_sequence")
  private Long reflectionId;
  private String content;
  private Visibility visibility;
  private LocalDate creationDate;

  @ManyToOne
  @JoinColumn(name="book_id", nullable=false)
  @ToString.Exclude
  @JsonIgnore
  private Book book;
}
