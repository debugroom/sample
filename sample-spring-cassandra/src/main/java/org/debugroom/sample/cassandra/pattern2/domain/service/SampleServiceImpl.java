package org.debugroom.sample.cassandra.pattern2.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Affiliation;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern2.domain.repository.AffiliationRepository;
import org.debugroom.sample.cassandra.pattern2.domain.repository.UserRepository;

@Service("sampleService")
public class SampleServiceImpl implements SampleService<User, Address, Email, Group>{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AffiliationRepository affiliationRepository;
	
	@Override
	public List<User> getUsers(){
        return getUsersByGroup(Group.builder().groupId(Long.valueOf(1)).build());
//		return (List<User>)userRepository.findAll();
	}

	@Override
	public User getUser(String loginId) {
		return userRepository.findUserByLoginId(loginId);
	}

	public List<User> getUsersByGroup(Group group){
		List<Affiliation> affiliations = affiliationRepository
				.findByAffiliationKeyGroupId(group.getGroupId());
		List<Long> userIds = new ArrayList<Long>();
		for(Affiliation affiliation : affiliations){
			userIds.add(affiliation.getAffiliationPK().getUserId());
		}
		return userRepository.findByUserIdIn(userIds);
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public List<Address> getAddresses() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Email> getEmails() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Group> getGroups() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User getUser(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Address getAddress(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> getUsers(String zipCd) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> getNotUsers(String zipCd) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User addAddress(Long userId, String zipCd, String address) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User updateAddress(Long userId, String zipCd, String address) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User deleteAddress(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Email> getEmails(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User addEmail(Long userId, String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User addUserWithEmail(Long userId, String userName, String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User updateEmail(Long userId, String email, String newEmail) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User deleteEmail(Long userId, String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User deleteEmails(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Group> getGroups(String groupName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Group> getGroups(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> getUsersByGroupId(Long groupId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> getNotUsersByGroupId(Long groupId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Group addUserToGroup(Long userId,  Long groupId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Group deleteUserFromGroup(Long userId, Long groupId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Group deleteGroup(Long groupId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User deleteUser(Long userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
