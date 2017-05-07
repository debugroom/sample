package org.debugroom.sample.cassandra.app.web;

import java.util.List;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1")
@RestController
public class SampleRestController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getUsers(){
		return sampleService.getUsers();
	}
	
	@RequestMapping(value="addresses", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getAddresses(){
		return sampleService.getAddresses();
	}
	
	@RequestMapping(value="emails", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getEmails(){
		return sampleService.getEmails();
	}

	@RequestMapping(value="groups", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getGroups(){
		return sampleService.getGroups();
	}

	@RequestMapping(value="authentication/{loginId}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResource login(@PathVariable String loginId){
		return UserResource.builder()
				.user(sampleService.getUser(loginId))
				.build();
	}

	@RequestMapping(value="users/{userId}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResource getUserResource(@PathVariable Long userId){
		return UserResource.builder()
				.user(sampleService.getUser(userId))
				.build();
	}

	@RequestMapping(value="users/{userId}/address", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResource getAddress(@PathVariable Long userId){
		return UserResource.builder()
				.user(sampleService.getAddress(userId))
				.build();
	}

	@RequestMapping(value="address/{zipCd}/users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getUsersByZipCd(@PathVariable String zipCd){
		return sampleService.getUsers(zipCd);
	}
	
	@RequestMapping(value="address/{zipCd}/notusers", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getNotUsersByZipCd(@PathVariable String zipCd){
		return sampleService.getNotUsers(zipCd);
	}

	@RequestMapping(value="users/{userId}/address", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public UserResource addAddress(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.addAddress(userId, inputParam.getZipCd(), inputParam.getAddress()))
				.build();
	}

	@RequestMapping(value="users/{userId}/address", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public UserResource updateAddress(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.updateAddress(userId, 
						inputParam.getZipCd(), inputParam.getAddress()))
				.build();
	}

	@RequestMapping(value="users/{userId}/address", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public UserResource deleteAddress(@PathVariable Long userId){
		return UserResource.builder()
				.user(sampleService.deleteAddress(userId))
				.build();
	}

	@RequestMapping(value="users/{userId}/emails", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getEmailsByUserId(@PathVariable Long userId){
		return sampleService.getEmails(userId);
	}

	@RequestMapping(value="emails/{email}/user", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResource getUserByEmail(@PathVariable String email){
		return UserResource.builder()
				.user(sampleService.getUserByEmail(email))
				.build();
	}

	@RequestMapping(value="users/{userId}/email", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public UserResource addEmail(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.addEmail(userId, inputParam.getEmail()))
				.build();
	}

	@RequestMapping(value="users/{userId}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public UserResource addUserWithEmail(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.addUserWithEmail(
						userId, inputParam.getUserName(), inputParam.getEmail()))
				.build();
	}

	@RequestMapping(value="users/{userId}/email", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public UserResource updateEmail(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.updateEmail(userId, 
						inputParam.getEmail(), inputParam.getNewEmail()))
				.build();
	}

	@RequestMapping(value="users/{userId}/email", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public UserResource deleteEmail(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return UserResource.builder()
				.user(sampleService.deleteEmail(userId, inputParam.getEmail()))
				.build();
	}

	@RequestMapping(value="users/{userId}/emails", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public UserResource deleteEmail(@PathVariable Long userId){
		return UserResource.builder()
				.user(sampleService.deleteEmails(userId))
				.build();
	}

	@RequestMapping(value="group", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getGroupsByGroupName(@RequestParam String groupName){
		return sampleService.getGroups(groupName);
	}

	@RequestMapping(value="users/{userId}/groups", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getGroupsByUserId(@PathVariable Long userId){
		return sampleService.getGroups(userId);
	}

	@RequestMapping(value="groups/{groupId}/users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getUsersByGroupId(@PathVariable Long groupId){
		return sampleService.getUsersByGroupId(groupId);
	}

	@RequestMapping(value="groups/{groupId}/notusers", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getNotUsersByGroupId(@PathVariable Long groupId){
		return sampleService.getNotUsersByGroupId(groupId);
	}

	@RequestMapping(value="users/{userId}/group", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public GroupResource addUserToGroup(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return GroupResource.builder()
				.group(sampleService.addUserToGroup(userId, inputParam.getGroupId()))
				.build();
	}
	
	@RequestMapping(value="users/{userId}/group", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public GroupResource deleteUserFromGroup(@PathVariable Long userId,
			@RequestBody InputParam inputParam){
		return GroupResource.builder()
				.group(sampleService.deleteUserFromGroup(userId, inputParam.getGroupId()))
				.build();
	}
	
	@RequestMapping(value="groups/{groupId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public GroupResource deleteGroup(@PathVariable Long groupId){
		return GroupResource.builder()
				.group(sampleService.deleteGroup(groupId))
				.build();
	}
	
	@RequestMapping(value="users/{userId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public UserResource deleteUser(@PathVariable Long userId){
		return UserResource.builder()
				.user(sampleService.deleteUser(userId))
				.build();
	}



}
