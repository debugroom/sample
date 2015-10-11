package org.debugroom.sample.domain.service.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author org.debugroom
 *
 */
public class ServiceExecutorImpl implements ServiceExecutor{

	private ApplicationContext applicationContext;
	
	public ServiceExecutorImpl(String beanDefinitionFilePath){
		applicationContext = new ClassPathXmlApplicationContext(beanDefinitionFilePath);
	}
	
	@Override
	public void executeService(String target) {
		Service service = (Service)applicationContext.getBean(target, Service.class);
		service.execute();
	}

}
