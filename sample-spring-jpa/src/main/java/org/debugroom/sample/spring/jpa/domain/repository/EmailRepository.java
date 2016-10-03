package org.debugroom.sample.spring.jpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.EmailId;

public interface EmailRepository extends JpaRepository<Email, EmailId>{
}
