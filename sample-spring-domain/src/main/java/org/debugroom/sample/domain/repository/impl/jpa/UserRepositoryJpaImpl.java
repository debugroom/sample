package org.debugroom.sample.domain.repository.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.debugroom.sample.domain.model.User;
import org.debugroom.sample.domain.repository.UserRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class UserRepositoryJpaImpl implements UserRepository{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private static final String FIND_ALL_QUERY = "FROM User ORDER BY userId";
	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM User";
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	@Override
	public User findOne(String userId) {
		return (User)entityManager.find(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return (List<User>)entityManager.createQuery(FIND_ALL_QUERY).getResultList();
	}

	@Override
	public int getNumberOfUser() {
		return ((Long)(entityManager.createQuery(COUNT_QUERY)
				.getResultList().get(0))).intValue();
	}

	@Override
	public void saveUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(user);
		transaction.commit();
	}

	@Override
	public void updateUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(user);
		transaction.commit();
	}

	@Override
	public void deleteUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		user = entityManager.merge(user);
		entityManager.remove(user);
		transaction.commit();
	}

}
