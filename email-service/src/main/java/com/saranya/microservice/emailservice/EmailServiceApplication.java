package com.saranya.microservice.emailservice;


import java.util.Map;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;

import com.saranya.microservice.emailservice.domain.service.EmailServiceImpl;




@SpringBootApplication
@RabbitListener(queues = "spring-boot")
public class EmailServiceApplication {
	
	@Autowired
	private EmailServiceImpl sendMail;
	private static final Logger LOGGER = Logger.getLogger(EmailServiceApplication.class.getName());
	@Autowired
	public SimpleMailMessage template;
	
	@Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
          "Hi %s,\n%s\nRegards,\nTo-Do-List Team.");
        return message;
    }
	

	
	@RabbitHandler
    public void receive(Map<String,String> in) {
        String to = in.get("userName");
        String text = in.get("statusMessage");
        LOGGER.info("Startng to send email for user"+to);
        sendMail.sendSimpleMessageUsingTemplate(to,"To-Do-List Status Update",template, in);
    }
	
	

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
