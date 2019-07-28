package com.saranya.microservice.emailservice.domain;

import lombok.Data;

@Data
public class Mail {

  String from;

  String to;

  String subject;

  String message;

}