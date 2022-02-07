package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
