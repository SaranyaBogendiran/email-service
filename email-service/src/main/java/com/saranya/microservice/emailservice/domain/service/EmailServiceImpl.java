package com.saranya.microservice.emailservice.domain.service;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;
    private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class.getName() );
    

    public void sendSimpleMessage(
      String to, String subject, String text) {
    	try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            LOGGER.info("Email Sent");
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }
    
    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               SimpleMailMessage template,
                                               Map<String, String> tempaleArgs) {
    	System.out.println(tempaleArgs.get("firstName"));
        String text = String.format(template.getText(),tempaleArgs.get("firstName"),tempaleArgs.get("statusMessage"));  
        sendSimpleMessage(to, subject, text);
    }
}