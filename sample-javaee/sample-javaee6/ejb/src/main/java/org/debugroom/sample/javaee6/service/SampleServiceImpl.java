package org.debugroom.sample.javaee6.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.debugroom.sample.javaee6.domain.repository.UserRepository;
import org.debugroom.sample.javaee6.domain.repository.AddressRepository;

import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;
import org.debugroom.sample.javaee6.domain.model.entity.Address;

@Named("sampleService")
@Stateless
public class SampleServiceImpl implements SampleService{

	@EJB
	UserRepository userRepository;

	@EJB
	AddressRepository addressRepository;
	
	@Override
	public User findUserService(String companyId, String userId) {
		return userRepository.findOne(UserPK.builder()
												.companyId(companyId)
												.userId(userId)
												.build());
	}

	@Override
	public List<User> findUsersService() {
		return userRepository.findAll();
	}

	@Override
	public List<Address> findAddressService(String companyId, String userId){
		return addressRepository.findAllByUser(User.builder()
														.id(UserPK.builder()
																	.companyId(companyId)
																	.userId(userId)
																	.build())
														.build()
														);
	}

}
