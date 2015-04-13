package org.mce.teknoservice.config;

import org.mce.teknoservice.web.rest.AccountResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
public class DevRabbitConfiguration {
	
	private final Logger log = LoggerFactory.getLogger(DevRabbitConfiguration.class);
	
	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		log.debug("****************** dev rabbit connection faqctory...");
		
		ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		
		return connectionFactory ;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}
}