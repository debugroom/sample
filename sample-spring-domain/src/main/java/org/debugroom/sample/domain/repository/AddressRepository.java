package org.debugroom.sample.domain.repository;

import java.util.List;

import org.debugroom.sample.domain.model.Address;
import org.debugroom.sample.domain.model.AddressPK;

/**
 * 
 * @author org.debugroom
 *
 */
public interface AddressRepository {

	public Address findOne(AddressPK addressPK);
	
	public List<Address> findAll(AddressPK addressPK);
	
	public int getNumberOfAddress(AddressPK addressPK);
	
	public void saveAddress(Address address);

	public void updateAddress(Address address);

	public void deleteAddress(Address address);

}
