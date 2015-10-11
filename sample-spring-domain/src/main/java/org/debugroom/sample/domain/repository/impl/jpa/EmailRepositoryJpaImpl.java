package org.debugroom.sample.domain.repository.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.debugroom.sample.domain.model.Email;
import org.debugroom.sample.domain.model.EmailPK;
import org.debugroom.sample.domain.repository.EmailRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class EmailRepositoryJpaImpl implements EmailRepository{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final String FIND_ALL_BY_USER_ID_QUERY = " FROM Email e WHERE e.EmailPK.userId = :userId";
	private static final String COUNT_BY_USER_ID_QUERY = " SELECT COUNT(*) FROM Email e WHERE e.emailPK.userId = :userId";
	private static final String FIND_ONE_BY_EMAIL_QUERY = " FROM Email e WHERE e.email = :email";

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	@Override
	public Email findOne(EmailPK emailPK) {
		return (Email)entityManager
						.find(Email.class, emailPK);
	}

	@Override
	public List<Email> findAll(EmailPK emailPK) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getNumberOfEmail(EmailPK emailPK) {
		return ((Long)entityManager
				.createQuery(COUNT_BY_USER_ID_QUERY)
				.setParameter("userId", emailPK.getUserId())
				.getResultList().get(0)).intValue();
	}

	@Override
	public void saveEmail(Email email) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updateEmail(Email email) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteEmail(Email email) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public Email findOneByEmail(String email) {
		return ((Email)entityManager
				.createQuery(FIND_ONE_BY_EMAIL_QUERY)
				.setParameter("email", email)
				.getSingleResult());
	}

}
