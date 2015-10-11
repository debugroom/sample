package org.debugroom.sample.domain.service.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
		
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.debugroom.sample.domain.service.common.AnnotatedService;
import org.debugroom.sample.domain.repository.UserRepository;
import org.debugroom.sample.domain.model.CredentialPK;
import org.debugroom.sample.domain.model.User;
import org.debugroom.sample.domain.model.Credential;

/**
 * 
 * @author org.debugroom
 *
 */
@Service
public class JpaSampleService implements AnnotatedService {

	private static final Logger logger = LoggerFactory.getLogger(JpaSampleService.class);

	@Autowired
	@Qualifier("userRepositoryJpaImpl")
	private UserRepository userRepository;
	
	@Override
	public void execute() {
		
		User addUser = new User();
		String newUserId = StringUtils.right(
				((new StringBuffer()).append("00000000")
										.append(userRepository.getNumberOfUser()))
												.toString(), 8);
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
		
		addUser.setUserId(newUserId);
		addUser.setUserName("AddedByJpaSampleService");
		addUser.setLastUpdatedDateAndTime(new Date());
		addUser.setTimeZone(timeZone);
		
		CredentialPK passwordCredentialPK = new CredentialPK();
		passwordCredentialPK.setUserId(newUserId);
		passwordCredentialPK.setCredentialId("0000");
		
		CredentialPK accessTokenCredentialPK = new CredentialPK();
		accessTokenCredentialPK.setUserId(newUserId);
		accessTokenCredentialPK.setCredentialId("0001");
		
		Credential passwordCredential = new Credential();
		passwordCredential.setCredentialPK(passwordCredentialPK);
		passwordCredential.setCredentialType("password");
		passwordCredential.setCredentialKey("(・∀・)=3<全部飲むナリよ");
		passwordCredential.setExpiredDateAndTime(new Date());
		passwordCredential.setTimezone(timeZone);
		
		Credential accessTokenCredential = new Credential();
		accessTokenCredential.setCredentialPK(accessTokenCredentialPK);
		accessTokenCredential.setCredentialType("accessToken");
		accessTokenCredential.setCredentialKey("987654321poiuytrewqlkjhgfdsamnbvcxz");
		accessTokenCredential.setExpiredDateAndTime(new Date());
		accessTokenCredential.setTimezone(timeZone);
		
		Set<Credential> credentials = new HashSet<Credential>();
		credentials.add(passwordCredential);
		credentials.add(accessTokenCredential);
		
		addUser.setCredentials(credentials);
		userRepository.saveUser(addUser);
		
		logger.info("------- Added User --------");
		logger.info("[UserID] : " + addUser.getUserId() + " [UserName] : " + addUser.getUserName() + " [LastUpdate] : " + addUser.getLastUpdatedDateAndTime());
		for(Credential credential : addUser.getCredentials()){
		logger.info("[UserID] : " + credential.getCredentialPK().getUserId() + 
					" [CredentialID] : " + credential.getCredentialPK().getCredentialId() + 
					" [CredentialType] : " + credential.getCredentialType() +
					" [CredentialKey] : " + credential.getCredentialKey());
		}
		logger.info("------------------------");
		

		addUser.setUserName("UpdatedByJpaSampleService");
		for(Credential credential : addUser.getCredentials()){
			credential.setCredentialKey("(・ω・)=3");
		}
		
		userRepository.updateUser(addUser);
		
		List<User> users = userRepository.findAll();
		
		logger.info("-------User List after update--------");
		for(User user : users){
			logger.info("[UserID] : " + user.getUserId() + " [UserName] : " + user.getUserName() + "[LastUpdate] : " + user.getLastUpdatedDateAndTime());
			for(Credential credential : user.getCredentials()){
				logger.info("[UserID] : " + credential.getCredentialPK().getUserId() + 
							" [CredentialID] : " + credential.getCredentialPK().getCredentialId() + 
							" [CredentialType] : " + credential.getCredentialType() +
							" [CredentialKey] : " + credential.getCredentialKey());
			}
			logger.info("-----------");
		}
		logger.info("-------------------------------------");
		userRepository.deleteUser(addUser);
	}
	
	
}
