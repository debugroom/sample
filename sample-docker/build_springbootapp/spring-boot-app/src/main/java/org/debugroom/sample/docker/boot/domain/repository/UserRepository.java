package org.debugroom.sample.docker.boot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.docker.boot.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
}
