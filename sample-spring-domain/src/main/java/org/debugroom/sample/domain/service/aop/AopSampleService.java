package org.debugroom.sample.domain.service.aop;

import org.springframework.stereotype.Service;

import org.debugroom.sample.domain.service.common.AnnotatedService;

/**
 * 
 * @author org.debugroom
 *
 */
@Service
public class AopSampleService implements AnnotatedService{

	@Override
	public void execute() {
		try{
			Thread.sleep(500);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
