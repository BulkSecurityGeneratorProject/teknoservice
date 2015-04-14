package org.mce.teknoservice.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPSystemArchitecture {
	    /**
	     * A join point is in the web layer if the method is defined
	     * in a type in the org.mce.teknoservice.web package or any sub-package
	     * under that.
	     */
	    @Pointcut("within(org.mce.teknoservice.web..*)")
	    public void inWebLayer() {}

	    /**
	     * A join point is in the service layer if the method is defined
	     * in a type in the org.mce.teknoservice.service package or any sub-package
	     * under that.
	     */
	    @Pointcut("within(org.mce.teknoservice.service..*)")
	    public void inServiceLayer() {}

	    /**
	     * A join point is in the data access layer if the method is defined
	     * in a type in the org.mce.teknoservice.dao package or any sub-package
	     * under that.
	     */
	    @Pointcut("within(org.mce.teknoservice.dao..*)")
	    public void inDataAccessLayer() {}

	    /**
	     * A business service is the execution of any method defined on a service
	     * interface. This definition assumes that interfaces are placed in the
	     * "service" package, and that implementation types are in sub-packages.
	     *
	     * If you group service interfaces by functional area (for example,
	     * in packages org.mce.teknoservice.abc.service and org.mce.teknoservice.def.service) then
	     * the pointcut expression "execution(* org.mce.teknoservice..service.*.*(..))"
	     * could be used instead.
	     *
	     * Alternatively, you can write the expression using the 'bean'
	     * PCD, like so "bean(*Service)". (This assumes that you have
	     * named your Spring service beans in a consistent fashion.)
	     */
	    @Pointcut("execution(* org.mce.teknoservice..service.*.*(..))")
	    public void businessService() {}

	    /**
	     * A data access operation is the execution of any method defined on a
	     * dao interface. This definition assumes that interfaces are placed in the
	     * "dao" package, and that implementation types are in sub-packages.
	     */
	    @Pointcut("execution(* org.mce.teknoservice.repository.*.*(..))")
	    public void dataAccessOperation() {}
	    
	    @Pointcut("execution(* org.mce.teknoservice.queue.*.*(..))")
	    public void queueOperation() {}

	}