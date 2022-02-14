package com.groupfour.readersbay.controller;

import com.groupfour.readersbay.entity.User;
import com.groupfour.readersbay.entity.UserDTO;
import com.groupfour.readersbay.entity.VerificationToken;
import com.groupfour.readersbay.event.RegistrationCompleteEvent;
import com.groupfour.readersbay.service.EmailSenderService;
import com.groupfour.readersbay.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
@Log4j2
public class RegistrationController {

  @Autowired
  UserService userService;
  @Autowired
  EmailSenderService emailSender;

  @Autowired
  private ApplicationEventPublisher publisher;

  @PostMapping("/register")
  public String registerUser(@RequestBody UserDTO userDTO,
                             final HttpServletRequest request) {
    User user = userService.registerUser(userDTO);
    publisher.publishEvent(new RegistrationCompleteEvent(
        user,
        applicationUrl(request)
    ));
    return "Success";
  }

  @GetMapping("/verifyRegistration")
  public String verifyRegistration(@RequestParam("token") String token) {
    String result = userService.validateVerificationToken(token);
    if(result.equalsIgnoreCase("valid")) {
      return "User verified successfully";
    }

    return "Could not verify";
  }

  @GetMapping("/resendVerifyToken")
  public String resendVerificationToken(@RequestParam("token") String oldToken,
                                        HttpServletRequest request) {
    VerificationToken verificationToken =
        userService.generateNewVerificationToken(oldToken);
    User user = verificationToken.getUser();
    resendVerificationTokenEmail(user, applicationUrl(request), verificationToken.getToken());
    return "Verification link sent";
  }

  private void resendVerificationTokenEmail(@NotNull User user,
                                            String applicationUrl,
                                            String token) {
    emailSender.sendVerificationEmail(user.getEmail(), applicationUrl, token);
  }

  private @NotNull String applicationUrl(@NotNull HttpServletRequest request) {
    return "http://" + request.getServerName() + ":"
        + request.getServerPort() + request.getContextPath();
  }
}
