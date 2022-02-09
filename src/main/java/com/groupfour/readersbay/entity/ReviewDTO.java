package com.groupfour.readersbay.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
  private int score;
  private String text;
  private LocalDate date;
}
