package org.debugroom.sample.domain.repository.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import org.debugroom.sample.domain.model.Phone;
import org.debugroom.sample.domain.model.PhonePK;
import org.debugroom.sample.domain.repository.PhoneRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class PhoneRepositoryJpaImpl implements PhoneRepository{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final String FIND_ALL_BY_USER_ID_QUERY = " FROM Phone p WHERE p.PhonePK.userId = :userId";
	private static final String COUNT_BY_USER_ID_QUERY = " SELECT COUNT(*) FROM Phone p WHERE p.phonePK.userId = :userId";

	@Override
	public Phone findOne(PhonePK phonePK) {
		return (Phone)entityManager.find(Phone.class, phonePK);
	}

	@Override
	public List<Phone> findAll(PhonePK phonePK) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getNumberOfPhone(PhonePK phonePK) {
		return ((Long)entityManager
						.createQuery(COUNT_BY_USER_ID_QUERY)
						.setParameter("userId", phonePK.getUserId())
						.getResultList().get(0)).intValue();
	}

	@Override
	public void savePhone(Phone phone) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updatePhone(Phone phone) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deletePhone(Phone phone) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

}
