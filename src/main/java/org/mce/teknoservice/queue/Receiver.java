package org.mce.teknoservice.queue;

import java.security.Principal;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import org.mce.teknoservice.aop.logging.ReceiverQueue;
import org.mce.teknoservice.security.SecurityUtils;
import org.mce.teknoservice.web.websocket.dto.ActivityDTO;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		latch.countDown();
	}
	
	 @Inject
	 SimpMessageSendingOperations messagingTemplate;
	 
	 @ReceiverQueue
	 public void forwardReceivedMessage(String message) {
			System.out.println("Send Received <" + message + ">");
			
			ActivityDTO activityDTO = new ActivityDTO();
	        activityDTO.setSessionId("xxx");
	        activityDTO.setUserLogin(SecurityUtils.getCurrentLogin());
	        activityDTO.setPage(message);
	        messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
	        
	        //return message;
	 }

	public CountDownLatch getLatch() {
		return latch;
	}

}