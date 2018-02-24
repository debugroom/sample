package org.debugroom.sample.spring.cloud.function;

import java.util.Arrays;
import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ApiGatewayEventFunction implements 
	Function<Flux<APIGatewayProxyRequestEvent>, Flux<APIGatewayProxyResponseEvent>>{@Override

	public Flux<APIGatewayProxyResponseEvent> apply(Flux<APIGatewayProxyRequestEvent> t) {
		t.subscribe(s ->{
			log.info("body : " + s.getBody());
		});
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setBody("ResponseBody");
		return Flux.fromIterable(Arrays.asList(response));
	}

}
