package com.groupfour.readersbay.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {
  private String title;
  private String author;
  private String description;
  private String motivation;
}
