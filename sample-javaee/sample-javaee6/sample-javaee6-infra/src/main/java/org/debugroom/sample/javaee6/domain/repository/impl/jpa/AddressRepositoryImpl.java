package org.debugroom.sample.javaee6.domain.repository.impl.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.debugroom.sample.javaee6.domain.model.entity.Address;
import org.debugroom.sample.javaee6.domain.model.entity.AddressPK;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.repository.AddressRepository;

@Stateless
@Named("addressRepository")
public class AddressRepositoryImpl implements AddressRepository{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private static final String FIND_ALL_QUERY = "SELECT A FROM Address A, User U "
			+ "WHERE U.id.companyId = :companyId "
			+ "AND U.id.userId = :userId "
			+ "AND U.id.companyId = A.id.companyId "
			+ "AND U.id.userId = A.id.userId";
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	@Override
	public Address findOne(AddressPK addressPK) {
		return (Address)entityManager.find(Address.class, addressPK);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Address> findAllByUser(User user) {
		return (List<Address>)entityManager
				.createQuery(FIND_ALL_QUERY)
				.setParameter("companyId", user.getId().getCompanyId())
				.setParameter("userId", user.getId().getUserId())
				.getResultList();
	}

}
