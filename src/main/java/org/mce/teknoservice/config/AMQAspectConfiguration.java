package org.mce.teknoservice.config;

import org.mce.teknoservice.aop.logging.ForwardReceivedMessageAfterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
public class AMQAspectConfiguration {

    @Bean
    @Profile({Constants.SPRING_PROFILE_DEVELOPMENT,Constants.SPRING_PROFILE_CLOUD})
    public ForwardReceivedMessageAfterAspect forwardReceivedMessageAfterAspect() {
        return new ForwardReceivedMessageAfterAspect();
    }
}