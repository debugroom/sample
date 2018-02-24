package org.debugroom.sample.spring.cloud.function;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.S3Object;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class S3EventFunction implements 
	java.util.function.Function<Flux<S3EventNotification>, Flux<String>>{

	@Inject
	AmazonS3 amazonS3;

	/* TODO : Investigate error : Cannot deserialize mapping 
	 * See : https://stackoverflow.com/questions/46162163/aws-lambda-de-serializer-linkedhashmap-cannot-cast
	 *       https://github.com/markfisher/spring-cloud-function-adapters/issues/8
	 * "errorMessage": "java.util.LinkedHashMap cannot be cast to com.amazonaws.services.s3.event.S3EventNotification",
     * "errorType": "java.lang.ClassCastException",
     * "stackTrace": [
     * "reactor.core.publisher.LambdaSubscriber.onNext(LambdaSubscriber.java:130)",
     * "reactor.core.publisher.FluxJust$WeakScalarSubscription.request(FluxJust.java:91)",
     * "reactor.core.publisher.LambdaSubscriber.onSubscribe(LambdaSubscriber.java:89)",
     * "reactor.core.publisher.FluxJust.subscribe(FluxJust.java:68)",
     * "reactor.core.publisher.Flux.subscribe(Flux.java:6571)",
     * "reactor.core.publisher.Flux.subscribeWith(Flux.java:6738)",
     * "reactor.core.publisher.Flux.subscribe(Flux.java:6564)",
     * "reactor.core.publisher.Flux.subscribe(Flux.java:6528)",
     * "reactor.core.publisher.Flux.subscribe(Flux.java:6471)",
     * "org.debugroom.sample.spring.cloud.function.S3EventFunction.apply(S3EventFunction.java:48)",
     * "org.debugroom.sample.spring.cloud.function.S3EventFunction.apply(S3EventFunction.java:1)",
     * "org.springframework.cloud.function.adapter.aws.SpringFunctionInitializer.apply(SpringFunctionInitializer.java:146)",
	 */
	@Override
	public Flux<String> apply(Flux<S3EventNotification> event) {
		event
		.subscribe(s -> {
			if(0 != s.getRecords().size()){
				S3EventNotification.S3EventNotificationRecord record = s.getRecords().get(0);
				log.info(this.getClass().getName() + " : AWS Region : " + record.getAwsRegion());
				log.info(this.getClass().getName() + " : Event Name : " + record.getEventName());
				log.info(this.getClass().getName() + " : Event Source : " + record.getEventSource());
				log.info(this.getClass().getName() + " : Event Version : " + record.getEventVersion());
				log.info(this.getClass().getName() + " : Event Time : " + record.getEventTime());
				log.info(this.getClass().getName() + " : IPAddress : " + record.getRequestParameters().getSourceIPAddress());
				log.info(this.getClass().getName() + " : PrincipalId : " + record.getUserIdentity().getPrincipalId());
				log.info(this.getClass().getName() + " : Bucket : " + record.getS3().getBucket().getName());
				log.info(this.getClass().getName() + " : ARN : " + record.getS3().getBucket().getArn());
				log.info(this.getClass().getName() + " : OwnerIdentity : " + record.getS3().getBucket().getOwnerIdentity().getPrincipalId());
				log.info(this.getClass().getName() + " : ObjectKey : " + record.getS3().getObject().getKey());
				log.info(this.getClass().getName() + " : UrlDecodedKey : " + record.getS3().getObject().getUrlDecodedKey());
				log.info(this.getClass().getName() + " : Size : " + record.getS3().getObject().getSizeAsLong());
				log.info(this.getClass().getName() + " : Sequencer : " + record.getS3().getObject().getSequencer());
			}else{
				log.info("No event data.");
			}
		});
		return Flux.just("Process for S3 Notification event is complete.");
	}
	
}
