package org.mce.teknoservice.aop.logging;

import javax.inject.Inject;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mce.teknoservice.security.SecurityUtils;
import org.mce.teknoservice.web.websocket.dto.ActivityDTO;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Aspect
public class AfterReturningExample {
	
	@Inject
	SimpMessageSendingOperations messagingTemplate;

    @After("org.mce.teknoservice.queue.Receiver.forwardReceivedMessage() && args(message,..)")
    public void doAccessCheck(String message) {
    	ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setSessionId("xxx");
        activityDTO.setUserLogin(SecurityUtils.getCurrentLogin());
        activityDTO.setPage(message);
        messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
    }

}