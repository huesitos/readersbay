package com.groupfour.readersbay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "user_sequence",
      strategy = GenerationType.SEQUENCE
  )
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;

  @Column(length = 60)
  private String password;
  private String role;
  private boolean enabled = false;
}
