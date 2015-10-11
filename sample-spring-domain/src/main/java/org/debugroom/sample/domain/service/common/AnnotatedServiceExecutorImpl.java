package org.debugroom.sample.domain.service.common;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author org.debugroom
 *
 */
@Component
public class AnnotatedServiceExecutorImpl implements ServiceExecutor{

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void executeService(String target) {
		// Specify target name "proxy" in using DefaultPointcutAdvisor.
		Service service = (Service)applicationContext.getBean(target, Service.class);
		service.execute();
	}
	
}
