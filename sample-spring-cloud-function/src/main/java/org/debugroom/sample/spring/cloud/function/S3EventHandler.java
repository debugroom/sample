package org.debugroom.sample.spring.cloud.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.event.S3EventNotification;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class S3EventHandler extends 
	SpringBootRequestHandler<S3EventNotification, String>{
	
	public S3EventHandler(){
		super();
	}

	public S3EventHandler(Class<?> configurationClass) {
		super(configurationClass);
	}

	/*
	 * handleRequestをオーバーライドして型を指定しないとjava.lang.ClassCastExceptionが発生する模様。
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
	 */
	@Override
	public Object handleRequest(S3EventNotification event, Context context){
		return super.handleRequest(event, context);
	}

}
