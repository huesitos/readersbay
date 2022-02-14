package com.groupfour.readersbay.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Log4j2
public class EmailSenderService {
  @Autowired
  private final JavaMailSender mailSender;

  public EmailSenderService() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost("smtp.gmail.com");
    sender.setPort(587);

    sender.setUsername("finanzaspersonaleskodigo@gmail.com");
    sender.setPassword("finanzaspersonales");

    Properties props = sender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    mailSender = sender;
  }

  public void sendSimpleEmail(String toEmail, String body, String subject) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("finanzaspersonaleskodigo@gmail.com");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);

    mailSender.send(message);
    log.info(message);
  }

  public void sendVerificationEmail(String toEmail, String applicationUrl, String token) {
    log.info("Sending email with url: " + applicationUrl);
    sendSimpleEmail(
        toEmail,
        "Click on the link to verify account: "
            + applicationUrl + "/verifyRegistration?token=" + token,
        "Verify account"
    );
  }
}
