package org.debugroom.sample.docker.boot.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.debugroom.sample.docker.boot.domain.entity.User;
import org.debugroom.sample.docker.boot.domain.repository.UserRepository;

public class SampleServiceImpl implements SampleService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
