package org.mce.teknoservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(Constants.SPRING_PROFILE_CLOUD)
public class CloudRabbitConfiguration {
	
	private final Logger log = LoggerFactory.getLogger(CloudRabbitConfiguration.class);
	
	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		log.debug("****************** cloud rabbit connection faqctory...");
		CloudFactory cloudFactory = new CloudFactory();
		Cloud cloud = cloudFactory.getCloud();
		AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo("rabbitmq");
		String serviceID = serviceInfo.getId();
		log.debug("****************** rabbit connection factory, rabbitmq service info id: "+serviceID);
		return cloud.getServiceConnector(serviceID, ConnectionFactory.class,
				null);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}
}