package org.debugroom.sample.docker.cllient;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestClient {

	private static final String SERVER_URL = "http://localhost:8080/";
	
	private static final String APP_NAME = "api/v1/";
	
	public static void main(String[] args){
		String response = executeGetUsersRequest(new RestTemplate());
		
		log.info(RestClient.class.getName() + " : " + response);
	}

	private static String executeGetUsersRequest(RestTemplate restTemplate){
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users", String.class);
	}
}
