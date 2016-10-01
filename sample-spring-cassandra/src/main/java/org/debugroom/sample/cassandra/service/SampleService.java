package org.debugroom.sample.cassandra.service;

import java.util.List;

import org.debugroom.sample.cassandra.entity.User;

public interface SampleService {

	public List<User> getUsers();
	
}
