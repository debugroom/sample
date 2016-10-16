package org.debugroom.sample.spring.jpa.domain.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.Affiliation;
import org.debugroom.sample.spring.jpa.domain.entity.AffiliationPK;
import org.debugroom.sample.spring.jpa.domain.entity.Group;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.repository.GroupRepository;
import org.debugroom.sample.spring.jpa.domain.repository.AddressRepository;
import org.debugroom.sample.spring.jpa.domain.repository.UserRepository;
import org.debugroom.sample.spring.jpa.domain.repository.specification.FindGroupsByUserId;
import org.debugroom.sample.spring.jpa.domain.repository.specification.FindUsersByGroupName;
import org.debugroom.sample.spring.jpa.domain.repository.specification.FindUsersByNotGroupName;

@Slf4j
@Transactional
public class ManyToManySampleServiceImpl implements ManyToManySampleService{

	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public List<Group> getGroups(User user) {
		List<Group> groups = groupRepository.findAll(
				FindGroupsByUserId.builder()
				                  .userId(user.getUserId())
				                  .build());
		log.info(this.getClass().getName() + " : groups of " + user.getUserId());
		if(groups.size()==0){
			log.info(this.getClass().getName() + "        - {null,null}");
		}
		for(Group group : groups){
			log.info(this.getClass().getName() + "        - {"
					+ group.getGroupId() + ", " + group.getGroupName() + "}");

		}
		return groups;
	}

	@Override
	public List<User> getUsersWith(Group group) {
		List<User> users = userRepository.findAll(
				FindUsersByGroupName.builder()
									.groupName(group.getGroupName())
									.build());
		log.info(this.getClass().getName() + " : users of " + group.getGroupName());
		if(users.size()==0){
			log.info(this.getClass().getName() + "        - {null,null}");
		}
		for(User user : users){
			log.info(this.getClass().getName() + "        - {"
					+ user.getUserId() + ", " + user.getUserName() + "}");
		}
		return users;
	}

	@Override
	public List<User> getUsersWithout(Group group) {
		List<User> users = userRepository.findAll(
				FindUsersByNotGroupName.builder()
									   .groupName(group.getGroupName())
									   .build());
		log.info(this.getClass().getName() + " : no users of " + group.getGroupName());
		if(users.size()==0){
			log.info(this.getClass().getName() + "        - {null,null}");
		}
		for(User user : users){
			log.info(this.getClass().getName() + "        - {"
					+ user.getUserId() + ", " + user.getUserName() + "}");
		}
		return users;
	}

	@Override
	public User addUserTo(Group group, User user) {
		String sequence = new StringBuilder()
								.append("00000000")
								.append(userRepository.count())
								.toString();
		String newUserId = sequence.substring(
				sequence.length()-8, sequence.length());
		user.setUserId(newUserId);
		String groupId = groupRepository.findByGroupName(group.getGroupName()).getGroupId();
		user.addAffiliation(Affiliation.builder()
										.id(AffiliationPK.builder()
															.userId(newUserId)
															.groupId(groupId)
															.build())
										.build());
		return userRepository.save(user);
	}

	@Override
	public User deleteUserFrom(Group group, User user) {
		Group findGroup = groupRepository.findByGroupName(group.getGroupName());
		for(Iterator<Affiliation> iterator = findGroup.getAffiliations().iterator(); 
				iterator.hasNext(); ){
			Affiliation affiliation = iterator.next();
			if(affiliation.getId().getUserId().equals(user.getUserId())){
				iterator.remove();
			}
		}
		return user;
	}

	@Override
	public Group deleteGroup(Group group) {
		Group findGroup = groupRepository.findByGroupName(group.getGroupName());
		groupRepository.delete(findGroup);
		return findGroup;
	}

	@Override
	public User deleteUser(User user) {
		User findUser = userRepository.findOne(user.getUserId());
		Address address = addressRepository.findOne(user.getUserId());
		addressRepository.delete(address);
		userRepository.delete(findUser);
		return user;
	}

}
