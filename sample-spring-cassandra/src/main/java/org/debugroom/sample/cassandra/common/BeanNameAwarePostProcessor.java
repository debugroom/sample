package org.debugroom.sample.cassandra.common;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BeanNameAwarePostProcessor implements BeanPostProcessor{
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
	/*
		log.info(this.getClass().getName() + " print out : call after create : " + bean.getClass().getName());
	 */
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) 
			throws BeansException {
		log.info(this.getClass().getName() + " print out : call before create bean name: " + bean.getClass().getName());
		return bean;
	}


}
