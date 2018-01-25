package org.debugroom.sample.spring.boot.test.dozer;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleService {

	public void testSample(SampleDestinationBean sampleDestinationBean){
		log.info(this.getClass().getName() + " : " + sampleDestinationBean.toString());
	}

}
