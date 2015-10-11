package org.debugroom.sample.domain.repository;

import java.util.List;

import org.debugroom.sample.domain.model.Phone;
import org.debugroom.sample.domain.model.PhonePK;

/**
 * 
 * @author org.debugroom
 *
 */
public interface PhoneRepository {

	public Phone findOne(PhonePK phonePK);
	
	public List<Phone> findAll(PhonePK phonePK);
	
	public int getNumberOfPhone(PhonePK phonePK);
	
	public void savePhone(Phone phone);
	
	public void updatePhone(Phone phone);
	
	public void deletePhone(Phone phone);
	
}
