package com.nimshub.softwarearchitecturedemo.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author Nirmala : 17:July:2023
 * This service implements all methods required to handle the business logic of SMTPGmail
 */
@Service
@RequiredArgsConstructor
public class SMTPGmailService implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void send(String receiver, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
        System.out.println("Mail has been sent...");
    }
}