package org.debugroom.sample.cassandra.pattern1.domain.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dozer.Mapper;

import com.datastax.driver.core.UDTValue;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import org.debugroom.sample.cassandra.domain.service.SampleService;
import org.debugroom.sample.cassandra.pattern1.domain.entity.User;
import org.debugroom.sample.cassandra.pattern1.domain.entity.UserOfGroup;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern1.domain.entity.AddressPK;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern1.domain.entity.CredentialPK;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern1.domain.entity.GroupOfUser;
import org.debugroom.sample.cassandra.pattern1.domain.repository.UserRepository;
import org.debugroom.sample.cassandra.pattern1.domain.repository.AddressRepository;
import org.debugroom.sample.cassandra.pattern1.domain.repository.CredentialRepository;
import org.debugroom.sample.cassandra.pattern1.domain.repository.EmailRepository;
import org.debugroom.sample.cassandra.pattern1.domain.repository.GroupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("sampleService")
public class SampleServiceImpl implements SampleService<User, Address, Email, Group>{

	@Autowired
	Mapper mapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	CredentialRepository credentialRepository;
	
	@Override
	public void execute(){
	}

	@Override
	public List<User> getUsers() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public List<Address> getAddresses() {
		return (List<Address>)addressRepository.findAll();
	}

	@Override
	public List<Email> getEmails() {
		return (List<Email>) emailRepository.findAll();
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
	public User getUser(String loginId) {
		Credential credential = credentialRepository.findOne(CredentialPK.builder()
				.credentialType("PASSWORD").loginId(loginId).build());
		if(null == credential){
			return null;
		}
		return userRepository.findOne(credential.getUserId());
	}

	@Override
	public Address getAddress(Long userId) {
		UDTValue udtValue = userRepository.findOne(userId).getAddress();
		if(udtValue == null){
			return null;
		}
		return Address.builder()
						.addresspk(AddressPK.builder()
										.userId(userId)
										.zipCd(udtValue.getString("zip_cd"))
										.build())
						.address(udtValue.getString("address"))
						.ver(udtValue.getInt("ver"))
						.lastUpdatedDate(udtValue.getTimestamp("last_updated_date"))
						.build();
	}

	@Override
	public List<User> getUsers(String zipCd) {
		List<Address> addresses = addressRepository.findByAddresspkZipCd(zipCd);
		List<Long> userIds = new ArrayList<Long>();
		for(Address address : addresses){
			userIds.add(address.getAddresspk().getUserId());
		}
		return userRepository.findByUserIdIn(userIds);
	}

	@Override
	public List<User> getNotUsers(String zipCd) {
		/* Not_In statement is no-supported for cassandra.
		List<Address> addresses = addressRepository.findByAddresspkZipCd(zipCd);
		List<Long> userIds = new ArrayList<Long>();
		for(Address address : addresses){
			userIds.add(address.getAddresspk().getUserId());
		}
		return userRepository.findByUserIdNotIn(userIds);
		*/
		List<Address> addresses = addressRepository.findByAddresspkZipCd(zipCd);
		Map<Long, User> usersMap = userRepository.findAllForMap();
		for(Address address : addresses){
			usersMap.remove(address.getAddresspk().getUserId());
		}
		return new ArrayList<User>(usersMap.values());
	}

	@Override
	public User addAddress(Long userId, String zipCd, String address) {
		Address saveAddress = Address.builder()
									.addresspk(AddressPK.builder()
														.userId(userId)
														.zipCd(zipCd)
														.build())
									.address(address)
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();
		addressRepository.save(saveAddress);
		User user = userRepository.findOne(userId);
		return userRepository.addAddress(user, zipCd, address);
	}

	@Override
	public User updateAddress(Long userId, String zipCd, String address) {
		Address updateAddress = addressRepository.findOne(
				AddressPK.builder().userId(userId).zipCd(zipCd).build());
		updateAddress.setAddress(address);
		updateAddress.setVer(updateAddress.getVer()+1);
		updateAddress.setLastUpdatedDate(new Date());
		addressRepository.save(updateAddress);
		User user = userRepository.findOne(userId);
		UDTValue udtValue = user.getAddress();
		udtValue.setString("address", address);
		udtValue.setInt("ver", udtValue.getInt("ver")+1);
		udtValue.setTimestamp("last_updated_date", new Date());
		return userRepository.save(user);
	}

	@Override
	public User deleteAddress(Long userId) {
		List<Address> addresses = addressRepository.findByAddresspkUserId(userId);
		for(Address address : addresses){
			addressRepository.delete(address);
		}
		User user = userRepository.findOne(userId);
		user.setAddress(null);
		return userRepository.save(user);
	}

	@Override
	public List<Email> getEmails(Long userId) {
		return emailRepository.findByUserId(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findOne(
				emailRepository.findOne(email).getUserId());
	}

	@Override
	public User addEmail(Long userId, String email) {
		Email addEmail = Email.builder()
								.email(email)
								.userId(userId)
								.ver(0)
								.lastUpdatedDate(new Date())
								.build();
		emailRepository.save(addEmail);
		List<Email> emails = emailRepository.findByUserId(userId);
		emails.add(addEmail);
		User user = userRepository.findOne(userId);
		user.setEmails(emails);
		return user;
	}

	@Override
	public User addUserWithEmail(Long userId, String userName, String email) {
		Email addEmail = Email.builder()
						.email(email)
						.userId(userId)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		emailRepository.save(addEmail);
		List<Email> emails = new ArrayList<Email>();
		emails.add(addEmail);
		User addUser = User.builder()
						.userId(userId)
						.userName(userName)
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(false)
						.emails(emails)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		return userRepository.save(addUser);
	}

	@Override
	public User updateEmail(Long userId, String email, String newEmail) {
		/* Primary key updating equals new saving row.
		Email updateEmail = emailRepository.findOne(email);
		updateEmail.setEmail(newEmail);
		emailRepository.save(updateEmail);
		 */
		Email deleteEmail = emailRepository.findOne(email);
		emailRepository.delete(deleteEmail);
		emailRepository.save(Email.builder()
									.email(newEmail)
									.userId(userId)
									.ver(0)
									.lastUpdatedDate(new Date())
									.build());
		User user = userRepository.findOne(userId);
		user.setEmails(emailRepository.findByUserId(userId));
		return user;
	}

	@Override
	public User deleteEmail(Long userId, String email) {
		Email deleteEmail = emailRepository.findOne(email);
		emailRepository.delete(deleteEmail);
		User user = userRepository.findOne(userId);
		user.setEmails(emailRepository.findByUserId(userId));
		return user;
	}

	@Override
	public User deleteEmails(Long userId) {
		List<Email> emails = emailRepository.findByUserId(userId);
		for(Email email : emails){
			emailRepository.delete(email);
		}
		return userRepository.findOne(userId);
	}

	@Override
	public List<Group> getGroups(String groupName) {
		return groupRepository.findByGroupName(groupName);
	}

	@Override
	public List<Group> getGroups(Long userId) {
		List<GroupOfUser> groupsOfUser =  getGroupsOfUser(userId);
		return Lists.transform(groupsOfUser, new Function<GroupOfUser, Group>() {
			@Override
			public Group apply(GroupOfUser groupOfUser){
				return mapper.map(groupOfUser, Group.class); 
			}
		});
	}
	
	public List<GroupOfUser> getGroupsOfUser(Long userId){
		return userRepository.findOne(userId).getGroups();
	}

	@Override
	public List<User> getUsersByGroupId(Long groupId) {
		List<UserOfGroup> usersOfGroup = getUsersOfGroup(groupId);
		return Lists.transform(usersOfGroup, new Function<UserOfGroup, User>(){
			@Override
			public User apply(UserOfGroup userOfGroup){
				return mapper.map(userOfGroup, User.class);
			}
		});
	}

	public List<UserOfGroup> getUsersOfGroup(Long groupId){
		return groupRepository.findOne(groupId).getUsers();
	}

	@Override
	public List<User> getNotUsersByGroupId(Long groupId) {
		List<UserOfGroup> usersOfGroup = groupRepository.findOne(groupId).getUsers();
		Map<Long, User> userMap = userRepository.findAllForMap();
		for(UserOfGroup userOfGroup : usersOfGroup){
			userMap.remove(userOfGroup.getUserId());
		}
		return new ArrayList<User>(userMap.values());
	}

	@Override
	public Group addUserToGroup(Long userId, Long groupId) {
		User user = userRepository.findOne(userId);
		Group group = groupRepository.findOne(groupId);
		group.getUsers().add(UserOfGroup.builder()
								.userId(userId)
								.userName(user.getUserName())
								.isEnabled(user.isEnabled())
								.isLocked(user.isLocked())
								.isAdmin(user.isAdmin())
								.ver(0)
								.lastUpdatedDate(new Date())
								.build());
		user.getGroups().add(GroupOfUser.builder()
								.groupId(groupId)
								.groupName(group.getGroupName())
								.ver(0)
								.lastUpdatedDate(new Date())
								.build());
		userRepository.save(user);
		return groupRepository.save(group);
	}

	@Override
	public Group deleteUserFromGroup(Long userId, Long groupId) {
		User user = userRepository.findOne(userId);
		List<GroupOfUser> groupsOfUser = new ArrayList<GroupOfUser>();
		for(GroupOfUser groupOfUser : user.getGroups()){
			if(groupId != groupOfUser.getGroupId()){
				groupsOfUser.add(groupOfUser);
			}
		}
		user.setGroups(groupsOfUser);
		Group group = groupRepository.findOne(groupId);
		List<UserOfGroup> usersOfGroup = new ArrayList<UserOfGroup>();
		for(UserOfGroup userOfGroup : group.getUsers()){
			if(userId != userOfGroup.getUserId()){
				usersOfGroup.add(userOfGroup);
			}
		}
		group.setUsers(usersOfGroup);
		userRepository.save(user);
		return groupRepository.save(group);
	}

	@Override
	public Group deleteGroup(Long groupId) {
		Group deleteGroup = groupRepository.findOne(groupId);
		for(UserOfGroup userOfGroup : deleteGroup.getUsers()){
			User user = userRepository.findOne(userOfGroup.getUserId());
			List<GroupOfUser> groupsOfUser = new ArrayList<GroupOfUser>();
			for(GroupOfUser groupOfUser : user.getGroups()){
				if(groupOfUser.getGroupId() != groupId){
					groupsOfUser.add(groupOfUser);
				}
			}
			user.setGroups(groupsOfUser);
			userRepository.save(user);
		}
		groupRepository.delete(deleteGroup);
		return deleteGroup;
	}

	@Override
	public User deleteUser(Long userId) {
		User deleteUser = userRepository.findOne(userId);
		for(GroupOfUser groupOfUser : deleteUser.getGroups()){
			Group group = groupRepository.findOne(groupOfUser.getGroupId());
			List<UserOfGroup> usersOfGroup = new ArrayList<UserOfGroup>();
			for(UserOfGroup userOfGroup : group.getUsers()){
				if(userId != userOfGroup.getUserId()){
					usersOfGroup.add(userOfGroup);
				}
			}
			group.setUsers(usersOfGroup);
			groupRepository.save(group);
		}
		for(Address address : addressRepository.findByAddresspkUserId(userId)){
			addressRepository.delete(address);
		}
		for(Email email : emailRepository.findByUserId(userId)){
			emailRepository.delete(email);
		}
		for(Credential credential : credentialRepository.findByUserId(userId)){
			credentialRepository.delete(credential);
		}
		userRepository.delete(deleteUser);
		return deleteUser;
	}

}
