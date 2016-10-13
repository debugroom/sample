package org.debugroom.sample.spring.jpa.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.EmailPK;

public interface EmailRepository extends JpaRepository<Email, EmailPK>{
	
	public List<Email> findByIdUserId(String userId);

}
