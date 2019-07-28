//package com.saranya.microservice.emailservice.infra.rabbitmq;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.rabbitmq.RabbitFlux;
//import lombok.val;
//import reactor.rabbitmq.*;
//
//@Configuration
//public class RabbitMQConfiguration {
//	private final String pass;
//
//	  private final String user;
//
//	  private final String host;
//
//	  private final Integer port;
//
//	  private final String mailQueue;
//
//	  public RabbitMQConfiguration(@Value("${spring.rabbitmq.password}") String pass,
//	      @Value("${spring.rabbitmq.username}") String user,
//	      @Value("${spring.rabbitmq.host}") String host,
//	      @Value("${spring.rabbitmq.port}") Integer port,
//	      @Value("${mail.queue}") String mailQueue) {
//	    this.pass = pass;
//	    this.user = user;
//	    this.host = host;
//	    this.port = port;
//	    this.mailQueue = mailQueue;
//	  }
//
//	  @Bean("springConnectionFactory")
//	  public ConnectionFactory connectionFactory() {
//	    CachingConnectionFactory factory = new CachingConnectionFactory();
//	    factory.setUsername(this.user);
//	    factory.setPassword(this.pass);
//	    factory.setHost(this.host);
//	    factory.setPort(this.port);
//	    return factory;
//	  }
//
//	  @Bean
//	  public AmqpAdmin amqpAdmin(@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
//	    return new RabbitAdmin(connectionFactory);
//	  }
//
//	  @Bean
//	  public TopicExchange emailExchange() {
//	    return new TopicExchange("email", true, false);
//	  }
//
//	  @Bean
//	  public Queue mailQueue() {
//	    return new Queue(this.mailQueue, true, false, false);
//	  }
//
//	  @Bean
//	  public Binding mailExchangeBinding(Queue mailQueue) {
//	    return BindingBuilder.bind(mailQueue).to(emailExchange()).with("*");
//	  }
//
//	  @Bean
//	  public Receiver receiver() {
//	    ReceiverOptions options = new ReceiverOptions();
//	    com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
//	    connectionFactory.setUsername(this.user);
//	    connectionFactory.setPassword(this.pass);
//	    connectionFactory.setPort(this.port);
//	    connectionFactory.setHost(this.host);
//	    options.connectionFactory(connectionFactory);
//	    return RabbitFlux.createReceiver(options);
//	    
//	  }
//
//}
