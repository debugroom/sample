package org.debugroom.sample.cassandra.domain.repository;

import org.debugroom.sample.cassandra.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
}
