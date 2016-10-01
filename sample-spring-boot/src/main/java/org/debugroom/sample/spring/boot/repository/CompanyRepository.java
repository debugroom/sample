package org.debugroom.sample.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.spring.boot.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String>{
}
