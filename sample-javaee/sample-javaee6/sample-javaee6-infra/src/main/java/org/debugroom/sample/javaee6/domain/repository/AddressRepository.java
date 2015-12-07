package org.debugroom.sample.javaee6.domain.repository;

import java.util.List;

import org.debugroom.sample.javaee6.domain.model.entity.Address;
import org.debugroom.sample.javaee6.domain.model.entity.AddressPK;
import org.debugroom.sample.javaee6.domain.model.entity.User;

public interface AddressRepository {

	public Address findOne(AddressPK addressPK);
	public List<Address> findAllByUser(User user);

}
