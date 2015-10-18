package org.debugroom.sample.javaee6.domain.repository.impl.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.inject.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;
import org.debugroom.sample.javaee6.domain.repository.UserRepository;

@Stateless
@Named("userRepository")
public class UserRepositoryImpl implements UserRepository{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private static final String FIND_ALL_QUERY = "FROM User U ORDER BY U.id.companyId, U.id.userId";

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	@Override
	public User findOne(UserPK userPK) {
		return (User)entityManager.find(User.class, userPK);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>)entityManager.createQuery(FIND_ALL_QUERY).getResultList();
	}

}
