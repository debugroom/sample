package org.debugroom.sample.common.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{

	private static final Logger logger = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("#postProcessAfterInitialization method has executed. beanName : " + beanName);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("#postProcessBeforeInitialization method has executed. beanName : " + beanName);
		return bean;
	}

}
