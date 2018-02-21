package org.debugroom.sample.spring.cloud.function;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.S3Object;

import reactor.core.publisher.Flux;

//@Component
public class S3EventFunction implements 
	java.util.function.Function<S3EventNotification, String>{

	@Inject
	AmazonS3 amazonS3;
	
	@Override
	public String apply(S3EventNotification event) {
		S3EventNotification.S3EventNotificationRecord record = event.getRecords().get(0);
		System.out.println(record.toString());
		return "Complete!";
		/*
		event.subscribe(s -> {
			for(Map.Entry<String, List<?>> entry : s.entrySet()){
				s.get(entry.getKey()).get(0);
				System.out.println(entry.getKey() + " : " + s.get(entry.getKey()).get(0).toString() + " : " + s.get(entry.getKey()).get(0).getClass().getName());
			}
		});
		return Flux.just("Complete");
		 */

		/*
		S3EventNotification notification = event.blockFirst();
		if(null != notification){
			System.out.println(this.getClass().getName() +  " : " + notification.toString());
		}else{
			System.out.println(this.getClass().getName() + " : No notifications.");
		}
		return Flux.just("Hello!");
		 */
	}

}
