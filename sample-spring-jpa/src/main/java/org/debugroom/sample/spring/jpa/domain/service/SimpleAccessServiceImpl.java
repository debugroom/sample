package org.debugroom.sample.spring.jpa.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.Group;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.repository.UserRepository;
import org.debugroom.sample.spring.jpa.domain.repository.AddressRepository;
import org.debugroom.sample.spring.jpa.domain.repository.EmailRepository;
import org.debugroom.sample.spring.jpa.domain.repository.GroupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleAccessServiceImpl implements SimpleAccessService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Override
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		log.info(this.getClass().getName() + " : users ");
		for(User user : users){
			log.info(this.getClass().getName() + "            - {"
					+ user.getUserId() + ", " + user.getUserName() + "}");
		}
		return users;
	}

	@Override
	public List<Address> getAddressList() {
		List<Address> addressList = addressRepository.findAll();
		log.info(this.getClass().getName() + " : addressList ");
		for(Address address : addressList){
			log.info(this.getClass().getName() + "            - {"
					+ address.getUserId() + ", " + address.getAddress() + "}");
		}
		return addressList;
	}

	@Override
	public List<Email> getEmails() {
		List<Email> emails = emailRepository.findAll();
		log.info(this.getClass().getName() + " : emails ");
		for(Email email : emails){
			log.info(this.getClass().getName() + "            - {"
					+ email.getId().getUserId() + ", " + email.getId().getEmailId() 
					+ "," + email.getEmail() + "}");
		}
		return emails;
	}

	@Override
	public List<Group> getGroups() {
		List<Group> groups = groupRepository.findAll();
		log.info(this.getClass().getName() + " : groups ");
		for(Group group : groups){
			log.info(this.getClass().getName() + "            - {"
					+ group.getGroupId() + ", " + group.getGroupName() + "}");
		}
		return groups;
	}

}
