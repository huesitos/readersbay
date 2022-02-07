package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
    @Column(nullable = false)
    private Long bookId;
    @Column(nullable = false, length = 200)
    @NotBlank
    private String title;
    private String description;
    @Column(nullable = false, length = 150)
    private String author;
    private String motivation;
}
