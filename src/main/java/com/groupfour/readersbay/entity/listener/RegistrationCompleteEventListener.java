package com.groupfour.readersbay.entity.listener;

import com.groupfour.readersbay.entity.User;
import com.groupfour.readersbay.event.RegistrationCompleteEvent;
import com.groupfour.readersbay.service.EmailSenderService;
import com.groupfour.readersbay.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class RegistrationCompleteEventListener implements
    ApplicationListener<RegistrationCompleteEvent> {

  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(@NotNull RegistrationCompleteEvent event) {
    log.info("Registration complete event");
    // Create Verification Token for the user with link
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    userService.saveVerificationTokenForUser(token, user);
    // send mail to user
    sendVerificationEmail(user.getEmail(), event.getApplicationUrl(), token);
  }

  private void sendVerificationEmail(String toEmail, String applicationUrl, String token) {
    EmailSenderService emailSender = new EmailSenderService();
    emailSender.sendVerificationEmail(toEmail, applicationUrl, token);
  }
}
