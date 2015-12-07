package org.debugroom.sample.javaee6.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

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
import org.debugroom.sample.javaee6.domain.model.entity.Address;
import org.debugroom.sample.javaee6.domain.model.entity.AddressPK;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;
import org.debugroom.sample.javaee6.domain.repository.impl.jpa.AddressRepositoryImpl;

@RunWith(Enclosed.class)
public class AddressRepositoryImplTest {

	@Category(UnitTests.class)
	@RunWith(Enclosed.class)
	public static class UnitTest{
		
		EntityManagerFactory entityManagerFactory;
		EntityTransaction entityTransaction;
		
		AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
		
		@Before
		public void setUp() throws Exception{
			entityManagerFactory = Persistence.createEntityManagerFactory("test-sample-app");
			addressRepository.setEntityManagerFactory(entityManagerFactory);
		}
		
		@RunWith(Theories.class)
		public static class normalTestCase1_findOne extends UnitTest{
			
			@DataPoints
			public static FindAddressFixture[] findAddressFixture = {
					new FindAddressFixture.FindAddressFixtureBuilder()
						.addressPK(new AddressPK("0000000000", "00000000", 1))
						.expected(Address.builder()
											.id(AddressPK.builder()
															.companyId("0000000000")
															.userId("00000000")
															.build())
											.postCd("1358671")
											.address("東京都江東区豊洲３－３－９")
											.addressDetail("豊洲センタービルアネックス12F北側")
											.build()
											)
						.build()
			};
			
			@Theory
			@Category(TestsWithDatabaseAccess.class)
			public void normalTestCase1_findOneForAddress(FindAddressFixture fixture){
				Address address = super.addressRepository.findOne(fixture.addressPK);
				
				assertThat(fixture.toString(), address.getPostCd(),
						is(fixture.expected.getPostCd()));
				assertThat(fixture.toString(), address.getAddress(),
						is(fixture.expected.getAddress()));
				assertThat(fixture.toString(), address.getAddressDetail(),
						is(fixture.expected.getAddressDetail()));
			}
			
			@Data
			@Builder
			@AllArgsConstructor
			public static class FindAddressFixture{
				AddressPK addressPK;
				Address expected;
			}
		}
		
		@RunWith(Theories.class)
		public static class normalTestCase2_findAll extends UnitTest{
			
			@DataPoints
			public static FindAllFixture[] findAllFixture = {
					new FindAllFixture.FindAllFixtureBuilder()
						.user(User.builder()
									.id(UserPK.builder()
												.companyId("0000000000")
												.userId("00000000")
												.build())
									.build()
						)
						.expected(new ArrayList<Address>(){
										{
											add(Address.builder()
														.id(AddressPK.builder()
																		.companyId("0000000000")
																		.userId("00000000")
																		.addressNo(1)
																		.build()
														)
														.postCd("1358671")
														.address("東京都江東区豊洲３－３－９")
														.addressDetail("豊洲センタービルアネックス12F北側")
														.build()
													);
										}
							}
						)
						.build()
			};
			
			@Theory
			@Category(TestsWithDatabaseAccess.class)
			public void normalTestCase2_fnaAllByUser(FindAllFixture fixture){
				
				List<Address> addresses = super.addressRepository.findAllByUser(fixture.user);
				
				assertThat(fixture.toString(), addresses.get(0).getId().getCompanyId(),
						is(fixture.expected.get(0).getId().getCompanyId()));
				assertThat(fixture.toString(), addresses.get(0).getId().getUserId(),
						is(fixture.expected.get(0).getId().getUserId()));
				assertThat(fixture.toString(), addresses.get(0).getId().getAddressNo(),
						is(fixture.expected.get(0).getId().getAddressNo()));
				assertThat(fixture.toString(), addresses.get(0).getPostCd(),
						is(fixture.expected.get(0).getPostCd()));
				assertThat(fixture.toString(), addresses.get(0).getAddress(),
						is(fixture.expected.get(0).getAddress()));
				assertThat(fixture.toString(), addresses.get(0).getAddressDetail(),
						is(fixture.expected.get(0).getAddressDetail()));

			}

			@Data
			@Builder
			@AllArgsConstructor
			public static class FindAllFixture{
				User user;
				List<Address> expected;
			}
		}
		
	}
}
