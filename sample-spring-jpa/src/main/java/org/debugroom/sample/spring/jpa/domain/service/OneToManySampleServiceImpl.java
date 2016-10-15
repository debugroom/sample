package org.debugroom.sample.spring.jpa.domain.service;

import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.EmailPK;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.repository.AddressRepository;
import org.debugroom.sample.spring.jpa.domain.repository.EmailRepository;
import org.debugroom.sample.spring.jpa.domain.repository.UserRepository;

@Slf4j
@Transactional
public class OneToManySampleServiceImpl implements OneToManySampleService{

	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Set<Email> getEmails(User user) {
		User findUser = userRepository.findOne(user.getUserId());
		log.info(this.getClass().getName() + " : emails of " + user.getUserId());
		for(Email email : findUser.getEmails()){
			log.info(this.getClass().getName() + "           - {"
					+ email.getId().getEmailId() + ", " + email.getEmail() + "}");
		}
		return findUser.getEmails();
	}

	@Override
	public User getUser(String email) {
		Email findEmail = emailRepository.findByEmail(email);
		return findEmail.getUsr();
	}

	@Override
	public User addEmail(User user, String email) {
		User findUser = userRepository.findOne(user.getUserId());
		String sequence = new StringBuilder()
								.append("00000000")
								.append(findUser.getEmails().size())
								.toString();
		String newEmailId = sequence.substring(
				sequence.length()-8, sequence.length());
		Email newEmail = Email.builder()
								.id(EmailPK.builder()
										.userId(findUser.getUserId())
										.emailId(newEmailId)
										.build())
								.email(email)
								.build();
		findUser.addEmail(newEmail);
		return findUser;
	}

	@Override
	public User addUser(User user, String email) {
		String sequence = new StringBuilder()
								.append("00000000")
								.append(userRepository.count())
								.toString();
		String newUserId = sequence.substring(
				sequence.length()-8, sequence.length());
		user.setUserId(newUserId);
		user.addEmail(Email.builder().id(EmailPK.builder()
												.userId(newUserId)
												.emailId("00000000")
												.build())
									 .email(email)
									 .build());
		User addUser = userRepository.save(user);
		addressRepository.save(Address.builder().userId(newUserId).build());
		return addUser;
	}

	@Override
	public User updateEmail(User user, Email email) {
		User findUser = userRepository.findOne(user.getUserId());
		for(Email updateEmail : findUser.getEmails()){
			if(updateEmail.getId().getEmailId().equals(
					email.getId().getEmailId())){
				updateEmail.setEmail(email.getEmail());
			}
		}
		return findUser;
	}

	@Override
	public User deleteEmail(User user, Email email) {
		User findUser = userRepository.findOne(user.getUserId());
		for(Iterator<Email> iterator = findUser.getEmails().iterator(); iterator.hasNext();){
			Email deleteEmail = iterator.next();
			if(deleteEmail.getId().getEmailId().equals(
					email.getId().getEmailId())){
				iterator.remove();
			}
		}
		return findUser;
	}

	@Override
	public User deleteEmails(User user) {
		User findUser = userRepository.findOne(user.getUserId());
		findUser.getEmails().clear();
		return findUser;
	}

	@Override
	public void deleteUser(User user) {
		User findUser = userRepository.findOne(user.getUserId());
		Address address = addressRepository.findOne(user.getUserId());
		addressRepository.delete(address);
		userRepository.delete(findUser);
	}

}
