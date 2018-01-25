package org.debugroom.sample.spring.boot.test.dozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Data
public class SampleNestBean {

	public SampleNestBean(){}
	
	private String test1;
	private Integer test2;

}
