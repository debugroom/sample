package org.debugroom.sample.domain.repository.impl.mybatis2;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.debugroom.sample.domain.model.User;
import org.debugroom.sample.domain.repository.UserRepository;

/**
 * 
 * @author org.debugroom
 *
 */
@Repository
public class UserRepositoryMybatis2Impl implements UserRepository{

	@Override
	public User findOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getNumberOfUser() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void saveUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updateUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
