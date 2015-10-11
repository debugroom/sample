package org.debugroom.sample.domain.repository;

import java.util.List;

import org.debugroom.sample.domain.model.Email;
import org.debugroom.sample.domain.model.EmailPK;

/**
 * 
 * @author org.debugroom
 *
 */
public interface EmailRepository {

	public Email findOne(EmailPK emailPK);

	public Email findOneByEmail(String email);

	public List<Email> findAll(EmailPK emailPK);

	public int getNumberOfEmail(EmailPK emailPK);

	public void saveEmail(Email email);

	public void updateEmail(Email email);

	public void deleteEmail(Email email);

}
