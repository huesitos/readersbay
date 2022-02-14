package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.User;
import com.groupfour.readersbay.entity.UserDTO;
import com.groupfour.readersbay.entity.VerificationToken;

public interface UserService {
  User registerUser(UserDTO userDTO);

  void saveVerificationTokenForUser(String token, User user);

  String validateVerificationToken(String token);

  VerificationToken generateNewVerificationToken(String oldToken);
}
