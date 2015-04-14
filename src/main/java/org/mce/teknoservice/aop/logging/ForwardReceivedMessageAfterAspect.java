package org.mce.teknoservice.aop.logging;

import javax.inject.Inject;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.mce.teknoservice.security.SecurityUtils;
import org.mce.teknoservice.web.websocket.dto.ActivityDTO;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Aspect
public class ForwardReceivedMessageAfterAspect {
	
	@Inject
	SimpMessageSendingOperations messagingTemplate;
	
	//@Pointcut("org.mce.teknoservice.queue.Receiver.forwardReceivedMessage() && args(message,..)")
	//@Pointcut("execution(org.mce.teknoservice.queue.Receiver.forwardReceivedMessage(java.lang.String))")
    //public void forwardReceivedMessagePointcut(String message) {}
	/*
	@Pointcut("org.mce.teknoservice.AOPSystemArchitecture.queueOperation() && args(message,..)")
	private void forwardReceivedMessagePointcut(String message) {}

    @AfterReturning(pointcut="forwardReceivedMessagePointcut(message)"
    		, returning="message")
    public void forwardReceivedMessage(String message) {
    	ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setSessionId("xxx");
        activityDTO.setUserLogin(SecurityUtils.getCurrentLogin());
        activityDTO.setPage(message);
        messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
    }
    */
    @Before("@annotation(org.mce.teknoservice.aop.logging.ReceiverQueueAdvice)")
    public void beforeReceiverQueueAdvice(){
        System.out.println("Executing myAdvice!!");
    }

}