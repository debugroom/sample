package org.debugroom.sample.spring.jpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.debugroom.sample.spring.jpa.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>, 
											JpaSpecificationExecutor<User>{
}
