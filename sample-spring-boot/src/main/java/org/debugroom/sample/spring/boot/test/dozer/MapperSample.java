package org.debugroom.sample.spring.boot.test.dozer;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MapperSample {

	@Autowired
	Mapper mapper;
	
	@Autowired
	SampleService sampleService;
	
	public void testMap(){
		SampleSourceBean sampleSourceBean = SampleSourceBean.builder()
				.test1("A")
				.test2(0)
				.sampleNestBean(
					SampleNestBean.builder()
					.test1("B")
					.test2(1)
					.build())
				.build();
		
		SampleDestinationBean sampleDestinationBean = 
				mapper.map(sampleSourceBean, SampleDestinationBean.class);
		
		log.info(this.getClass().getName() + " : " + sampleDestinationBean.toString());

	}
	
	public void testMap2(){
		SampleSourceBean sampleSourceBean = SampleSourceBean.builder()
				.test1("A")
				.test2(0)
				.sampleNestBean(
					SampleNestBean.builder()
					.test1("B")
					.test2(1)
					.build())
				.build();
		sampleService.testSample(mapper.map(sampleSourceBean, SampleDestinationBean.class));
	}

}
