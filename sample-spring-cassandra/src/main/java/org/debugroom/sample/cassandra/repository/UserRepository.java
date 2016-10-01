package org.debugroom.sample.cassandra.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.cassandra.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
}
