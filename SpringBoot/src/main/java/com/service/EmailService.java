package com.service;

import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment,String filename);
}
