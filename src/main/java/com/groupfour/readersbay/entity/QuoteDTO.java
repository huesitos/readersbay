package com.groupfour.readersbay.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuoteDTO {
  private String content;
  private Visibility visibility;
}
