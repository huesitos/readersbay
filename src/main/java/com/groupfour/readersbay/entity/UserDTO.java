package com.groupfour.readersbay.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String role;
}
