package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id_review", nullable = false)
    private Long id_review;
    @Column(name = "review", nullable = false)
    private String review;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "id_book", nullable = false)
    private Long id_book;
}
