package org.debugroom.sample.spring.cloud.function;

import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import lombok.extern.slf4j.Slf4j;

public class ApiGatewayEventHandler extends SpringBootApiGatewayRequestHandler{

	/* Warning :: This code is not work. It needs invesitigation as follow errror.
	 * TODO Investigating Exception has occurred because RequestBody is null 
	 * Cannot convert event: java.lang.IllegalStateException
     * java.lang.IllegalStateException: Cannot convert event
     * at org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler.deserializeBody(SpringBootApiGatewayRequestHandler.java:54)
     * at org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler.convertEvent(SpringBootApiGatewayRequestHandler.java:36)
     * at org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler.convertEvent(SpringBootApiGatewayRequestHandler.java:17)
     * at org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler.handleRequest(SpringBootRequestHandler.java:45)
     * at org.debugroom.sample.spring.cloud.function.ApiGatewayEventHandler.handleRequest(ApiGatewayEventHandler.java:14)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * Caused by: java.lang.NullPointerException
     * at com.fasterxml.jackson.core.JsonFactory.createParser(JsonFactory.java:879)
     * at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2858)
     * at org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler.deserializeBody(SpringBootApiGatewayRequestHandler.java:51)
	 */
	@Override
	public Object handleRequest(APIGatewayProxyRequestEvent event, Context context) {
		return super.handleRequest(event, context);
	}

}