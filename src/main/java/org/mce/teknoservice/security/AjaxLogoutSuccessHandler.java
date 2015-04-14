package org.mce.teknoservice.security;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mce.teknoservice.config.AuditEventPublisher;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security logout handler, specialized for Ajax requests.
 */
@Component
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {
	
	@Inject
    private AuditEventPublisher auditPublisher;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
    	
    	String principal = authentication.getName();
    	AuditEvent event = new AuditEvent(principal, "LOGOUT_END", new HashMap<String, Object>());
    	auditPublisher.publish(event);
    	
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
