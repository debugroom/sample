package org.debugroom.sample.javaee6.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import org.debugroom.sample.common.test.junit.category.UnitTests;
import org.debugroom.sample.common.test.junit.category.TestsWithDatabaseAccess;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;
import org.debugroom.sample.javaee6.domain.repository.impl.jpa.UserRepositoryImpl;

@RunWith(Enclosed.class)
public class UserRepositoryImplTest {
	
	@Category(UnitTests.class)
	@RunWith(Theories.class)
	public static class UnitTest{
		
		EntityManagerFactory entityManagerFactory;
		EntityTransaction entityTransaction;
		
		UserRepositoryImpl userRepository = new UserRepositoryImpl();
		
		@Before
		public void setUp() throws Exception{
			entityManagerFactory = Persistence.createEntityManagerFactory("test-sample-app");
			userRepository.setEntityManagerFactory(entityManagerFactory);
		}
		
		@DataPoints
		public static FindUserFixture[] findUserFixture = {
				new FindUserFixture.FindUserFixtureBuilder()
						.userPK(new UserPK("0000000000","00000000"))
						.expected((User.builder()
										.id(UserPK.builder()
													.companyId("00000000000")
													.userId("00000000")
													.build())
										.userName("Kohei Kawabata")
										.loginId("kohei.kawabata")
										.build()
									))
						.build()
		};

		@Theory
		@Category(TestsWithDatabaseAccess.class)
		public void normalTestCase1_findOneForUser(FindUserFixture fixture){
			User user = userRepository.findOne(fixture.userPK);
			
			assertThat(fixture.toString(), user.getUserName(),
					is(fixture.expected.getUserName()));
			assertThat(fixture.toString(), user.getLoginId(),
					is(fixture.expected.getLoginId()));
		}

		@Data
		@Builder
		@AllArgsConstructor
		public static class FindUserFixture{
			UserPK userPK;
			User expected;
		}
		                                    
	}
}
