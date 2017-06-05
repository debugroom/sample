package org.debugroom.sample.docker.boot.domain.service;

import java.util.List;

import org.debugroom.sample.docker.boot.domain.entity.User;

public interface SampleService {

	public List<User> getUsers();
	
}
