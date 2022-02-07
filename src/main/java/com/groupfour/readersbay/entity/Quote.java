package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Data
@Entity
@Table(name = "quotes")
public class Quote {
  @Id
  @SequenceGenerator(
      name = "quote_sequence",
      sequenceName = "quote_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_sequence")
  @Column(name = "quote_id", nullable = false)
  @Setter(AccessLevel.NONE)
  private Long quoteId;
  @NotBlank
  @Column(name = "content", nullable = false)
  private String content;
  @Column(name = "creation_date", nullable = false, updatable = false)
  private LocalDate creationDate;
  @Column(name = "visibility", nullable = false)
  private Visibility visibility;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id", referencedColumnName = "bookId")
  @ToString.Exclude
  private Book book;
}
