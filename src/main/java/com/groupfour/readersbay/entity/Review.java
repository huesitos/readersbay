package com.groupfour.readersbay.entity;

import lombok.*;

import javax.persistence.*;
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
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
    private Long id_review;
    @Column(name = "socore", length = 5, nullable = false)
    private int socore;
    @Column(name = "review", nullable = false)
    private String review;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    //@OneToOne(cascade = CascadeType.REMOVE)
    //@JoinColumn(name = "id_book", nullable = false)
    @Column(name = "id_book", nullable = false)
    private Long id_book;
}
