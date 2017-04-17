package org.debugroom.sample.cassandra.pattern1.domain.repository;

import java.util.List;

import org.debugroom.sample.cassandra.pattern1.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern1.domain.entity.AddressPK;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, AddressPK>{

	public List<Address> findByAddresspkZipCd(String zipCd);

	@Query("select * from address where user_id = ?0  allow filtering")
	public List<Address> findByAddresspkUserId(Long userId);

}
