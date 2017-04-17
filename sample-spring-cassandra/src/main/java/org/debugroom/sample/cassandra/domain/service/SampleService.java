package org.debugroom.sample.cassandra.domain.service;

import java.util.List;

public interface SampleService<U, A, E, G> {

	public void execute();
	
	public List<U> getUsers();
	
	public List<A> getAddresses();
	
	public List<E> getEmails();
	
	public List<G> getGroups();
	
	public U getUser(Long userId);
	
	public U getUser(String loginId);

	public A getAddress(Long userId);

	public List<E> getEmails(Long userId);
	
	public List<G> getGroups(String groupName);

	public List<U> getUsers(String zipCd);
	
	public List<U> getNotUsers(String zipCd);
	
	public U addAddress(Long userId, String zipCd, String address);

	public U updateAddress(Long userId, String zipCd, String address);
	
	public U deleteAddress(Long userId);

	public U getUserByEmail(String email);
	
	public U addEmail(Long userId, String email);
	
	public U addUserWithEmail(Long userId, String userName, String email);
	
	public U updateEmail(Long userId, String email, String newEmail);
	
	public U deleteEmail(Long userId, String email);
	
	public U deleteEmails(Long userId);
	
	public List<G> getGroups(Long userId);
	
	public List<U> getUsersByGroupId(Long groupId);
	
	public List<U> getNotUsersByGroupId(Long groupId);
	
	public G addUserToGroup(Long userId, Long groupId);
	
	public G deleteUserFromGroup(Long userId, Long groupId);
	
	public G deleteGroup(Long groupId);
	
	public U deleteUser(Long userId);

}
