package org.mce.teknoservice.config;

import javax.inject.Inject;

import org.mce.teknoservice.queue.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(value = {CloudRabbitConfiguration.class, DevRabbitConfiguration.class})
public class AMQConfiguration {

		final static String queueName = "spring-boot";

		@Autowired
		RabbitTemplate rabbitTemplate;

		@Bean
		Queue queue() {
			return new Queue(queueName, false);
		}

		@Bean
		TopicExchange exchange() {
			return new TopicExchange("spring-boot-exchange");
		}

		@Bean
		Binding binding(Queue queue, TopicExchange exchange) {
			return BindingBuilder.bind(queue).to(exchange).with(queueName);
		}

		@Inject ConnectionFactory rabbitConnectionFactory;
		
		@Bean
		SimpleMessageListenerContainer container(MessageListenerAdapter listenerAdapter) {
			/*ConnectionFactory connectionFactory = null; 
		
			try{
				connectionFactory = new CachingConnectionFactory("localhost");
			}catch(Exception e){
				connectionFactory = new CachingConnectionFactory("tiger.cloudamqp.com");
			}	*/
			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
			container.setConnectionFactory(rabbitConnectionFactory);
			container.setQueueNames(queueName);
			container.setMessageListener(listenerAdapter);
			return container;
		}

	    @Bean
	    Receiver receiver() {
	        return new Receiver();
	    }

		@Bean
		MessageListenerAdapter listenerAdapter(Receiver receiver) {
			//return new MessageListenerAdapter(receiver, "receiveMessage");
			return new MessageListenerAdapter(receiver, "forwardReceivedMessage");
		}

	    /*@PostConstruct
	    public void run() throws Exception {
	        System.out.println("Waiting five seconds...");
	        Thread.sleep(5000);
	        System.out.println("Sending message...");
	        rabbitTemplate.convertAndSend(queueName, "Hello from RabbitMQ!");
	        receiver().getLatch().await(10000, TimeUnit.MILLISECONDS);
	    }*/
}