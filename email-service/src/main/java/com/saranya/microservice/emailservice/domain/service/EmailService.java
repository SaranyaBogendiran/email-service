package com.saranya.microservice.emailservice.domain.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	void sendSimpleMessage(String to,
            String subject,
            String text);

	void sendSimpleMessageUsingTemplate(String to,
			String subject,
			SimpleMailMessage template,
			Map<String, String> tempaleArgs);
}
