package org.debugroom.sample.cassandra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.sample.cassandra.entity.User;
import org.debugroom.sample.cassandra.repository.UserRepository;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		Iterable<User> users = userRepository.findAll();
		return (List<User>)users;
	}

}
