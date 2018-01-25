package org.debugroom.sample.spring.boot.test.dozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Data
public class SampleDestinationBean {

	public SampleDestinationBean(){}
	
	private String test3;
	private Integer test4;
	private SampleNestBean sampleNestBean;
	
}
