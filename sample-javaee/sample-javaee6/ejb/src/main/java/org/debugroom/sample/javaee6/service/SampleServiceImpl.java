package org.debugroom.sample.javaee6.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.debugroom.sample.javaee6.domain.repository.UserRepository;

import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;

@Named("sampleService")
@Stateless
public class SampleServiceImpl implements SampleService{

	@Inject
	UserRepository userRepository;
	
	@Override
	public User findUserService(String companyId, String userId) {
		UserPK userPK = new UserPK(companyId, userId);
		return userRepository.findOne(userPK);
	}

}
