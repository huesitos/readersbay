package com.groupfour.readersbay.service;

import com.groupfour.readersbay.entity.User;
import com.groupfour.readersbay.entity.UserDTO;
import com.groupfour.readersbay.entity.VerificationToken;
import com.groupfour.readersbay.repository.UserRepository;
import com.groupfour.readersbay.repository.VerificationTokenRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private VerificationTokenRepository tokenRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(@NotNull UserDTO userDTO) {
    User user = User
        .builder()
        .email(userDTO.getEmail())
        .firstName(userDTO.getFirstName())
        .lastName(userDTO.getLastName())
        .password(passwordEncoder.encode(userDTO.getPassword()))
        .role(userDTO.getRole())
        .build();

    return userRepository.save(user);
  }

  @Override
  public void saveVerificationTokenForUser(String token, User user) {
    VerificationToken verificationToken = new VerificationToken(user, token);
    tokenRepository.save(verificationToken);
  }

  @Override
  public String validateVerificationToken(String token) {
    VerificationToken verificationToken = tokenRepository.findByToken(token);

    if(verificationToken == null) {
      return "invalid";
    }
    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();

    if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
      tokenRepository.delete(verificationToken);
      return "expired";
    }

    user.setEnabled(true);
    userRepository.save(user);
    return "valid";
  }

  @Override
  public VerificationToken generateNewVerificationToken(String oldToken) {
    VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
    verificationToken.setToken(UUID.randomUUID().toString());
    tokenRepository.save(verificationToken);
    return verificationToken;
  }
}
