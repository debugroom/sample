package org.debugroom.sample.cassandra.client;

import java.util.HashMap;
import java.util.Map;

import org.debugroom.sample.cassandra.app.web.InputParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestClient {

	private static final String SERVER_URL = "http://localhost:8080/";

	private static final String APP_NAME = "api/v1/";

	public static void main(String[] args){

//		String response = executeGetUsersRequest(new RestTemplate());
//		String response = executeGetEmailsRequest(new RestTemplate());
//		String response = executeGetGroupsRequest(new RestTemplate());
//		String response = executeLoginRequest(new RestTemplate());
//		String response = executeGetUserRequest(new RestTemplate());
//		String response = executeGetAddressRequest(new RestTemplate());
//		String response = executeGetUsersByZipCdRequest(new RestTemplate());
//		String response = executeGetNotUsersByZipCdRequest(new RestTemplate());
//		String response = executeAddAddressRequest(new RestTemplate());
//		String response = executeUpdateAddressRequest(new RestTemplate());
//		String response = executeDeleteAddressRequest(new RestTemplate());
//		String response = executeGetEmailsByuserId(new RestTemplate());
//		String response = executeGetUserByEmail(new RestTemplate());
//		String response = executeAddEmailRequest(new RestTemplate());
//		String response = executeAddUserWithEmailRequest(new RestTemplate());
//		String response = executeUpdateEmailRequest(new RestTemplate());
//		String response = executeDeleteEmailRequest(new RestTemplate());
//		String response = executeDeleteEmailsRequest(new RestTemplate());
//		String response = executeGetGroupsByGroupNameRequest(new RestTemplate());
//		String response = executeGetGroupsByUserIdRequest(new RestTemplate());
//		String response = executeGetUsersByGroupIdRequest(new RestTemplate());
//		String response = executeGetNotUsersByGroupIdRequest(new RestTemplate());
//		String response = executeAddUserToGroupRequest(new RestTemplate());
//		String response = executeDeleteUserFromGroupRequest(new RestTemplate());
//		String response = executeDeleteGroupRequest(new RestTemplate());
		String response = executeDeleteUserRequest(new RestTemplate());
		
		log.info(RestClient.class.getName() + " : " + response);
		
	}
	
	private static String executeGetUsersRequest(RestTemplate restTemplate){
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users", String.class);
	}

	private static String executeGetEmailsRequest(RestTemplate restTemplate){
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "emails", String.class);
	}

	private static String executeGetGroupsRequest(RestTemplate restTemplate){
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "groups", String.class);
	}

	private static String executeLoginRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("loginId", "test");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "authentication/{loginId}", String.class, paramMap);
	}
	
	private static String executeGetUserRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users/{userId}", String.class, paramMap);
	}
	
	private static String executeGetAddressRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/address", String.class, paramMap);
	}

	private static String executeGetUsersByZipCdRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("zipCd", "100-0001");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "address/{zipCd}/users", String.class, paramMap);
	}

	private static String executeGetNotUsersByZipCdRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("zipCd", "100-0001");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "address/{zipCd}/notusers", String.class, paramMap);
	}

	private static String executeAddAddressRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "2");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(2)).zipCd("200-0001").address("Chiba").build();
		return restTemplate.postForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/address", inputParam, String.class, paramMap);
	}

	private static String executeUpdateAddressRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(1)).zipCd("300-0001").address("Asaka").build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/address", HttpMethod.PUT, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
		
	}
	
	private static String executeDeleteAddressRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		InputParam inputParam = InputParam.builder().userId(Long.valueOf(1)).build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/address", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
		
	}
	
	private static String executeGetEmailsByuserId(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/emails", String.class, paramMap);
	}

	private static String executeGetUserByEmail(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", "test@test.com");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "emails/{email}/user", String.class, paramMap);
	}

	private static String executeAddEmailRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(1)).email("test4@test.com").build();
		return restTemplate.postForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/email", inputParam, String.class, paramMap);
	}

	private static String executeAddUserWithEmailRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "3");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(3)).userName("User1").email("test4@test.com").build();
		return restTemplate.postForObject(SERVER_URL + APP_NAME
				+ "users/{userId}", inputParam, String.class, paramMap);
	}

	private static String executeUpdateEmailRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(1)).email("test3@test.com").newEmail("test5@test.com").build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/email", HttpMethod.PUT, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
		
	private static String executeDeleteEmailRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "0");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(0)).email("test@test.com").build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/email", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
		
	private static String executeDeleteEmailsRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "0");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(0)).build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/emails", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
	
	private static String executeGetGroupsByGroupNameRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("groupName", "GroupA");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "group", String.class, paramMap);
	}

	private static String executeGetGroupsByUserIdRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "0");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/groups", String.class, paramMap);
	}

	private static String executeGetUsersByGroupIdRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("groupId", "1");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "groups/{groupId}/users", String.class, paramMap);
	}

	private static String executeGetNotUsersByGroupIdRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("groupId", "1");
		return restTemplate.getForObject(SERVER_URL + APP_NAME
				+ "groups/{groupId}/notusers", String.class, paramMap);
	}

	private static String executeAddUserToGroupRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "1");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(1)).groupId(Long.valueOf(2)).build();
		return restTemplate.postForObject(SERVER_URL + APP_NAME
				+ "users/{userId}/group", inputParam, String.class, paramMap);
	}
		
	private static String executeDeleteUserFromGroupRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "0");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(0))
				.groupId(Long.valueOf(1)).build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}/group", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
		
	private static String executeDeleteGroupRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("groupId", "0");
		InputParam inputParam = InputParam.builder()
				.groupId(Long.valueOf(0)).build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "groups/{groupId}", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
			
	private static String executeDeleteUserRequest(RestTemplate restTemplate){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", "0");
		InputParam inputParam = InputParam.builder()
				.userId(Long.valueOf(0)).build();
		return restTemplate.exchange(SERVER_URL + APP_NAME 
				+ "users/{userId}", HttpMethod.DELETE, 
				new HttpEntity<InputParam>(inputParam), String.class, paramMap).getBody();
	}
	
}
