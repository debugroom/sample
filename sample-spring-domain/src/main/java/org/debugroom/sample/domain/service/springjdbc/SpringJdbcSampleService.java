package org.debugroom.sample.domain.service.springjdbc;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
		
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.debugroom.sample.domain.service.common.AnnotatedService;
import org.debugroom.sample.domain.repository.UserRepository;
import org.debugroom.sample.domain.model.User;

/**
 * 
 * @author org.debugroom
 *
 */
@Service
public class SpringJdbcSampleService implements AnnotatedService {

	private static final Logger logger = LoggerFactory.getLogger(SpringJdbcSampleService.class);
	
	@Autowired
	@Qualifier("userRepositorySpringJdbcImpl")
	private UserRepository userRepository;
	
	@Override
	public void execute() {
		
		User addUser = userRepository.findOne("00000000");
		addUser.setUserId(StringUtils.right(
				(((new StringBuffer()).append("00000000")
										.append(userRepository.getNumberOfUser()))
												.toString()), 8));
		addUser.setUserName("AddedBySpringJdbcSampleService");
		addUser.setLastUpdatedDateAndTime(new Date());
		addUser.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		userRepository.saveUser(addUser);
		logger.info("------- Added User --------");
		logger.info("[UserID] : " + addUser.getUserId() + " [UserName] : " + addUser.getUserName() + " [LastUpdate] : " + addUser.getLastUpdatedDateAndTime());
		logger.info("------------------------");
		
		addUser.setUserName("UpdatedByConfirmSpringJdbcService");
		userRepository.updateUser(addUser);
		
		List<User> users = userRepository.findAll();
		
		logger.info("-------User List after update--------");
		for(User user : users){
			logger.info("[UserID] : " + user.getUserId() + " [UserName] : " + user.getUserName() + "[LastUpdate] : " + user.getLastUpdatedDateAndTime());
		}
		logger.info("-------------------------------------");
		
		userRepository.deleteUser(addUser);
		
	}

}
