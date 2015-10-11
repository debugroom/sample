package org.debugroom.sample.app.batch.item.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemWriter;

/**
 * 
 * @author debugroom.org
 *
 */
public class SampleItemWriter implements ItemWriter<Object>{

	private static final Logger logger = LoggerFactory.getLogger(SampleItemWriter.class);

	@Override
	public void write(List<? extends Object> data) throws Exception {
		for(Object target : data){
			logger.info(target.toString());
		}
	}
	
}
