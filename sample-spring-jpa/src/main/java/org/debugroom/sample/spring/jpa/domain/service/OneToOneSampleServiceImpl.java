package org.debugroom.sample.spring.jpa.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.repository.AddressRepository;
import org.debugroom.sample.spring.jpa.domain.repository.UserRepository;
import org.debugroom.sample.spring.jpa.domain.repository.specification.FindUsersByNotZipCd;
import org.debugroom.sample.spring.jpa.domain.repository.specification.FindUsersByZipCd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class OneToOneSampleServiceImpl implements OneToOneSampleService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address getAddress(User user) {
		Address address = addressRepository.findOne(user.getUserId());
		log.info(this.getClass().getName() + " : address of " + user.getUserId());
		if(address != null){
			log.info(this.getClass().getName() + "            - {"
					+ address.getZipCd() + ", " + address.getAddress() + "}");
		}else{
			log.info(this.getClass().getName() + "            - {null, null}");
		}
		return address;
	}

	@Override
	public List<User> getUsersWith(String zipCd) {
		List<User> users = userRepository.findAll(FindUsersByZipCd.builder()
													.zipCd(zipCd).build());
		log.info(this.getClass().getName() + " : users with zipCd " + zipCd);
		for(User user : users){
			log.info(this.getClass().getName() + "            - {"
					+ user.getUserId() + ", " + user.getUserName() + ", "
					+ user.getAddress().getZipCd() + ", " 
					+ user.getAddress().getAddress() + "}");
		}
		return users;
	}

	@Override
	public List<User> getUsersWithout(String zipCd) {
		List<User> users = userRepository.findAll(FindUsersByNotZipCd.builder()
													.zipCd(zipCd).build());
		log.info(this.getClass().getName() + " : users without zipCd " + zipCd);
		for(User user : users){
			log.info(this.getClass().getName() + "            - {"
					+ user.getUserId() + ", " + user.getUserName() + ", "
					+ user.getAddress().getZipCd() + ", " 
					+ user.getAddress().getAddress() + "}");
		}
		return users;
	}

	@Override
	public User addAddress(Address address) {
		String sequence = new StringBuilder()
								.append("00000000")
								.append(userRepository.count())
								.toString();
		String newUserId = sequence.substring(
				sequence.length()-8, sequence.length());
		address.setUserId(newUserId);
		User newUser = User.builder()
							.userId(newUserId)
							.userName("NewUser(・∀・)b")
							.loginId("loginId")
							.build();
		userRepository.save(newUser);
		newUser.setAddress(address);
		addressRepository.save(address);
		userRepository.flush();
		List<User> users = userRepository.findAll();
		log.info(this.getClass().getName() + " : users ");
		for(User user : users){
			log.info(this.getClass().getName() + "            - {"
					+ user.getUserId() + ", " + user.getUserName() + "}");
			if(user.getUserId().equals(newUser.getUserId())){
				Address newAddress = newUser.getAddress();
				log.info(this.getClass().getName() + "  Add Address - {"
						+ newAddress.getUserId() + ", " + newAddress.getAddress() + "}");
			}
		}
		return newUser;
	}

	@Override
	public void updateAddress(String userId, Address address) {
		Address updateAddress = getAddress(User.builder().userId(userId).build()); 
		updateAddress.setZipCd(address.getZipCd());
		updateAddress.setAddress(address.getAddress());
	}

	@Override
	public void deleteAddress(String userId) {
		Address address = addressRepository.findOne(userId);
		addressRepository.delete(address);
//		user.setAddress(null);
//		userRepository.save(user);
//		addressRepository.delete(user.getAddress());
	}

	@Override
	public void deleteUser(String userId) {
		User deleteUser = userRepository.findOne(userId);
		addressRepository.delete(userId);
		userRepository.delete(deleteUser);
		List<User> users = userRepository.findAll();
		log.info(this.getClass().getName() + " : users ");
		for(User user : users){
			log.info(this.getClass().getName() + "            - {"
					+ user.getUserId() + ", " + user.getUserName() + "}");
		}
	}


}
