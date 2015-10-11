package org.debugroom.sample.domain.repository.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.debugroom.sample.domain.model.Address;
import org.debugroom.sample.domain.model.AddressPK;
import org.debugroom.sample.domain.repository.AddressRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class AddressRepositoryJpaImpl implements AddressRepository {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final String FIND_ALL_BY_USER_ID_QUERY = " FROM Address a WHERE a.addressPK.userId = :userId";
	private static final String COUNT_BY_USER_ID_QUERY = " SELECT COUNT(*) FROM Address a WHERE a.addressPK.userId = :userId";

	@Override
	public Address findOne(AddressPK addressPK) {
		return (Address)entityManager
					.find(Address.class, addressPK);
	}

	@Override
	public List<Address> findAll(AddressPK addressPK) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getNumberOfAddress(AddressPK addressPK) {
		return ((Long)entityManager
				.createQuery(COUNT_BY_USER_ID_QUERY)
				.setParameter("userId", addressPK.getUserId())
						.getResultList().get(0)).intValue();
	}

	@Override
	public void saveAddress(Address address) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updateAddress(Address address) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteAddress(Address address) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

}
