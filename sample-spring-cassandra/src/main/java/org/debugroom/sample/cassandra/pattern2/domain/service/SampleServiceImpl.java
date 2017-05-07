package org.debugroom.sample.cassandra.pattern2.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Affiliation;
import org.debugroom.sample.cassandra.pattern2.domain.entity.AffiliationPK;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern2.domain.repository.AffiliationRepository;
import org.debugroom.sample.cassandra.pattern2.domain.repository.UserRepository;
import org.debugroom.sample.cassandra.pattern2.domain.repository.GroupRepository;

@Service("sampleService")
public class SampleServiceImpl implements SampleService<User, Address, Email, Group>{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	AffiliationRepository affiliationRepository;
	
	@Override
	public List<User> getUsers(){
		return (List<User>)userRepository.findAll();
	}

	@Override
	public User getUser(String loginId) {
		return userRepository.findUserByLoginId(loginId);
	}

	public List<User> getUsersByGroup(Group group){
		List<Affiliation> affiliations = affiliationRepository
				.findByAffiliationpkGroupId(group.getGroupId());
		List<Long> userIds = new ArrayList<Long>();
		for(Affiliation affiliation : affiliations){
			userIds.add(affiliation.getAffiliationpk().getUserId());
		}
		return userRepository.findByUserIdIn(userIds);
	}

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public List<Address> getAddresses() {
		List<User> users = getUsers();
		List<Address> addresses = new ArrayList<Address>();
		for(User user : users){
			addresses.add(user.getAddress());
		}
		return addresses;
	}

	@Override
	public List<Email> getEmails() {
		List<User> users = getUsers();
		List<Email> allEmails = new ArrayList<Email>();
		for(User user : users){
			if(null != user.getEmails()){
				allEmails.addAll(user.getEmails());
			}
		}
		return allEmails;
	}

	@Override
	public List<Group> getGroups() {
		return (List<Group>) groupRepository.findAll();
	}

	@Override
	public User getUser(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public Address getAddress(Long userId) {
		return userRepository.findOne(userId).getAddress();
	}

	@Override
	public List<User> getUsers(String zipCd) {
		/*
		List<User> users = new ArrayList<User>();
		for(User user : getUsers()){
			if(null != user.getAddress() 
					&& user.getAddress().getZipCd().equals(zipCd)){
				users.add(user);
			}
		}
		*/
		Map<String, List<User>> usersMapByZipCd = 
				userRepository.findAllForMappedListByZipCd();
		List<User> users = usersMapByZipCd.get(zipCd);
		return users;
	}

	@Override
	public List<User> getNotUsers(String zipCd) {
		/*
		List<User> users = new ArrayList<User>();
		for(User user : getUsers()){
			if(null != user.getAddress()
					&& !user.getAddress().getZipCd().equals(zipCd)){
				users.add(user);
			}
		}
		*/
		Map<String, List<User>> usersMapByZipCd =
				userRepository.findAllForMappedListByZipCd();
		usersMapByZipCd.remove(zipCd);
		List<User> users = new ArrayList<User>();
		for(Entry<String, List<User>> entry : usersMapByZipCd.entrySet()){
			users.addAll(entry.getValue());
		}
		return users;
	}

	@Override
	public User addAddress(Long userId, String zipCd, String address) {
		User user = getUser(userId);
		if(null != user){
			if(null == user.getAddress()){
				Address addAddress = Address.builder()
										.zipCd(zipCd)
										.address(address)
										.ver(0)
										.lastUpdatedDate(new Date())
										.build();
				user.setAddress(addAddress);
			}else{
				Address updateAddress = user.getAddress();
				updateAddress.setZipCd(zipCd);
				updateAddress.setAddress(address);
				updateAddress.setVer(updateAddress.getVer()+1);
				updateAddress.setLastUpdatedDate(new Date());
			}
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public User updateAddress(Long userId, String zipCd, String address) {
		return addAddress(userId, zipCd, address);
	}

	@Override
	public User deleteAddress(Long userId) {
		User user = getUser(userId);
		if(null != user){
			user.setAddress(null);
		}
		return user;
	}

	@Override
	public List<Email> getEmails(Long userId) {
		return userRepository.findOne(userId).getEmails();
	}

	@Override
	public User getUserByEmail(String email) {
		/*
		for(User user : getUsers()){
			if(null != user.getEmails()){
				for(Email target: user.getEmails()){
					if(target.getEmail().equals(email)){
						return user;
					}
				}
			}
		}
		return null;
		 */
		Map<String, User> usersMapByEmail = userRepository.findAllForMappByEmail();
		return usersMapByEmail.get(email);
	}

	@Override
	public User addEmail(Long userId, String email) {
		User user = userRepository.findOne(userId);
		user.getEmails().add(Email.builder()
				                  .email(email)
				                  .ver(0)
				                  .lastUpdatedDate(new Date())
				                  .build());
		userRepository.save(user);
		return user;
	}

	@Override
	public User addUserWithEmail(Long userId, String userName, String email) {
		List<Email> emails = new ArrayList<Email>();
		User user = User.builder()
				        .userId(userId)
				        .userName(userName)
				        .isEnabled(true)
				        .isLocked(false)
				        .isAdmin(false)
				        .emails(emails)
				        .ver(0)
				        .lastUpdatedDate(new Date())
				        .build();
		emails.add(Email.builder()
				        .email(email)
				        .ver(0)
				        .lastUpdatedDate(new Date())
				        .build());
		return userRepository.save(user);
	}

	@Override
	public User updateEmail(Long userId, String email, String newEmail) {
		User user = userRepository.findOne(userId);
		if(null != user){
			for(Email target : user.getEmails()){
				if(email.equals(target.getEmail())){
					target.setEmail(newEmail);
				}
			}
		}
		return userRepository.save(user);
	}

	@Override
	public User deleteEmail(Long userId, String email) {
		User user = userRepository.findOne(userId);
		List<Email> newEmails = new ArrayList<Email>();
		for(Email target : user.getEmails()){
			if(!email.equals(target.getEmail())){
				newEmails.add(target);
			}
		}
		user.setEmails(newEmails);
		return userRepository.save(user);
	}

	@Override
	public User deleteEmails(Long userId) {
		User user = userRepository.findOne(userId);
		user.setEmails(null);
		return userRepository.save(user);
	}

	@Override
	public List<Group> getGroups(String groupName) {
		return groupRepository.findByGroupName(groupName);
	}

	@Override
	public List<Group> getGroups(Long userId) {
		/*
		List<Affiliation> affiliations = 
				affiliationRepository.findByAffiliationpkUserId(userId);
		List<Long> groupIds = new ArrayList<Long>();
		for(Affiliation affiliation : affiliations){
			groupIds.add(affiliation.getAffiliationpk().getGroupId());
		}
		List<Group> groups = groupRepository.findByGroupIdIn(groupIds);
		 */
		Map<Long, List<Long>> groupIdsMap = affiliationRepository.findGroupIdsMapByUserId();
		List<Group> groups = groupRepository.findByGroupIdIn(groupIdsMap.get(userId));
		return groups;
	}

	@Override
	public List<User> getUsersByGroupId(Long groupId) {
		List<Affiliation> affiliations = 
				affiliationRepository.findByAffiliationpkGroupId(groupId);
		List<Long> userIds = new ArrayList<Long>();
		for(Affiliation affiliation : affiliations){
			userIds.add(affiliation.getAffiliationpk().getUserId());
		}
		List<User> users = userRepository.findByUserIdIn(userIds);
		return users;
	}

	@Override
	public List<User> getNotUsersByGroupId(Long groupId) {
		List<Affiliation> affiliations = 
				affiliationRepository.findByAffiliationpkGroupId(groupId);
		Map<Long, User> userMap = userRepository.findAllForMap();
		for(Affiliation affiliation : affiliations){
			userMap.remove(affiliation.getAffiliationpk().getUserId());
		}
		return new ArrayList<User>(userMap.values());
	}

	@Override
	public Group addUserToGroup(Long userId,  Long groupId) {
		Affiliation affiliation = Affiliation.builder()
				.affiliationpk(AffiliationPK.builder()
						                    .groupId(groupId)
						                    .userId(userId)
						                    .build())
				.build();
		affiliationRepository.save(affiliation);
		return groupRepository.findOne(groupId);
	}

	@Override
	public Group deleteUserFromGroup(Long userId, Long groupId) {
		Affiliation affiliation = affiliationRepository
				.findOne(AffiliationPK.builder()
						              .groupId(groupId)
						              .userId(userId)
						              .build());
		affiliationRepository.delete(affiliation);
		return groupRepository.findOne(groupId);
	}

	@Override
	public Group deleteGroup(Long groupId) {
		List<Affiliation> affiliations = 
				affiliationRepository.findByAffiliationpkGroupId(groupId);
		for(Affiliation affiliation : affiliations){
			affiliationRepository.delete(affiliation);
		}
		Group group = groupRepository.findOne(groupId);
		groupRepository.delete(group);
		return group;
	}

	@Override
	public User deleteUser(Long userId) {
		Map<Long, List<Long>> groupIdsMap = 
				affiliationRepository.findGroupIdsMapByUserId();
		List<Long> groupIds = groupIdsMap.get(userId);
		for(Long groupId : groupIds){
			affiliationRepository.delete(AffiliationPK.builder()
					.groupId(groupId).userId(userId).build());
		}
		User user = userRepository.findOne(userId);
		userRepository.delete(user);
		return user;
	}

}
