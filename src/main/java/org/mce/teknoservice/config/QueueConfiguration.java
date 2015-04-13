package org.mce.teknoservice.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
//@Configuration
public class QueueConfiguration {
	
	//@Bean
	public ConnectionFactory queueConnectionFactory(ConnectionFactory connectionFactory) throws Exception {
		//ConnectionFactory queueConnectionFactory = new CachingConnectionFactory(); 
		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);

		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.*"));

		// set up the listener and container
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		
		Object listener = new Object() {
			public void handleMessage(String foo) {
				System.out.println("*****************"+foo);
			}
		};
		
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();

		/* send something
		RabbitTemplate template = new RabbitTemplate(cf);
		template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
		Thread.sleep(1000);
		container.stop();*/
		return connectionFactory;
	}

}