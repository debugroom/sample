package org.debugroom.sample.domain.repository.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import org.debugroom.sample.domain.model.Credential;
import org.debugroom.sample.domain.repository.CredentialRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class CrendentialRepositoryJpaImpl implements CredentialRepository{

	private EntityManager entityManager;
	private static final String FIND_ALL_BY_USER_ID_QUERY = " FROM Credential WHERE userId = :userId";
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Credential> getCredentials(String userId) {
		return (List<Credential>)entityManager
				.createQuery(FIND_ALL_BY_USER_ID_QUERY)
				.setParameter("userId", userId)
				.getResultList();
	}

}
