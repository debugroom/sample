package org.debugroom.sample.spring.cloud.function.config;

import java.util.function.Function;

import org.debugroom.sample.spring.cloud.function.S3EventFunction;
import org.springframework.cloud.function.context.FunctionScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;

@FunctionScan
@Configuration
public class ProductionConfig {

	/* @FunctionScanを使用しない場合Bean登録しておく。
	@Bean
	public Function<S3EventNotification, String> s3EventFunction() {
		return event -> {
			S3EventNotification.S3EventNotificationRecord record = event.getRecords().get(0);
			System.out.println(record.toString());
			return "Complete!";
		};
	}
	 */
	
	@Bean
	public AmazonS3 amazonS3Client(){
		return AmazonS3ClientBuilder.defaultClient();
	}

}
