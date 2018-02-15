package org.debugroom.sample.spring.cloud.aws.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SampleObject implements Serializable{

	public SampleObject(){
	}

	private String partionKey;
	private String secondKey;
	private String date;

}
