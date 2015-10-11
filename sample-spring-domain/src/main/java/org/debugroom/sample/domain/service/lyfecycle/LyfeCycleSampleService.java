package org.debugroom.sample.domain.service.lyfecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.BeansException;

import org.debugroom.sample.domain.service.common.Service;

/**
 * 
 * @author org.debugroom
 *
 */
public class LyfeCycleSampleService implements Service
                                                ,BeanNameAware
                                                ,BeanFactoryAware
                                                ,InitializingBean
                                                ,DisposableBean
                                                {
	
	private static final Logger logger = LoggerFactory.getLogger(LyfeCycleSampleService.class);
	
	private String constructorString;
	private String setterString;
	private String beanName;
	private BeanFactory beanFactory;

	public LyfeCycleSampleService(String constructorString){
		this.constructorString = constructorString;
		logger.info("1. Bean Constructor has executed. [Param]" + this.constructorString);	
	}
	
	public void init(){
        logger.info("6. init method has executed");
	}
	
	@Override
	public void destroy() throws Exception {
        // This method is called by implementation of DisposableBean.
        logger.info("8. destroy method has executed");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
        // This method is called by implementation of InitializingBean.
        logger.info("5. property set has executed");		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // This method is called by implementation of BeanFactoryAware.
		this.beanFactory = beanFactory;
        logger.info("4. setBeanFactory has executed. beanFactory : " + this.beanFactory.getClass());
	}

	@Override
	public void setBeanName(String beanName) {
        // This method is called by implementation of BeanNameAware.
		this.beanName = beanName;
        logger.info("3. setBeanName has executed. beanName : " + this.beanName);
	}

	@Override
	public void execute() {
        // This method is called by service execution.
        logger.info("7. execute method has executed");
	}

	public String getSetterString() {
		return setterString;
	}

	public void setSetterString(String setterString) {
		this.setterString = setterString;
        logger.info("2. Setter Method has executed. [Param]" + this.setterString);
	}

}
