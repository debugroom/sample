package org.debugroom.sample.spring.jpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.spring.jpa.domain.entity.Address;

public interface AddressRepository extends JpaRepository<Address, String>{
}
