package org.debugroom.sample.cassandra.pattern1.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.debugroom.sample.cassandra.pattern1.domain.entity.User;
import org.debugroom.sample.cassandra.pattern1.domain.entity.UserOfGroup;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern1.domain.entity.AddressOfUser;
import org.debugroom.sample.cassandra.pattern1.domain.entity.AddressPK;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern1.domain.entity.CredentialPK;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern1.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern1.domain.entity.GroupOfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class InitilizingDbAspect {
	
	@Autowired 
	@Qualifier("cassandraTemplate")
	CassandraOperations operations;
	@Autowired 
	@Qualifier("cassandraAdminOperations")
	CassandraAdminOperations adminOperations;
	
	@Pointcut("execution(* org.debugroom.sample.cassandra.domain.service.*.*(..))")
	public void pointcut(){
	}
	
	@Before("pointcut()")
	public void before() throws ParseException{
		
		log.info(this.getClass().getName() + "#before() executed.");
		
		operations.truncate("address");
		operations.truncate("group");
		operations.truncate("email");
		operations.truncate("credential");
		operations.truncate("users");

		KeyspaceMetadata keyspaceMetadata = adminOperations.getKeyspaceMetadata();
		UserType userTypeAddress = keyspaceMetadata.getUserType("addressofuser");

		UDTValue udtValueAddress1 = userTypeAddress.newValue();
		udtValueAddress1.setString("zip_cd", "100-0001");
		udtValueAddress1.setString("address", "Tokyo");
		udtValueAddress1.setInt("ver", 0);
		udtValueAddress1.setTimestamp("last_updated_date", new Date());

		UDTValue udtValueAddress2 = userTypeAddress.newValue();
		udtValueAddress2.setString("zip_cd", "300-0001");
		udtValueAddress2.setString("address", "Saitama");
		udtValueAddress2.setInt("ver", 0);
		udtValueAddress2.setTimestamp("last_updated_date", new Date());

		GroupOfUser groupOfUserA = new GroupOfUser();
		groupOfUserA.setGroupId(Long.valueOf(0));
		groupOfUserA.setGroupName("GroupA");
		groupOfUserA.setVer(0);
		groupOfUserA.setLastUpdatedDate(new Date());

		GroupOfUser groupOfUserB = new GroupOfUser();
		groupOfUserB.setGroupId(Long.valueOf(1));
		groupOfUserB.setGroupName("GroupB");
		groupOfUserB.setVer(0);
		groupOfUserB.setLastUpdatedDate(new Date());

		GroupOfUser groupOfUserC = new GroupOfUser();
		groupOfUserC.setGroupId(Long.valueOf(2));
		groupOfUserC.setGroupName("GroupC");
		groupOfUserC.setVer(0);
		groupOfUserC.setLastUpdatedDate(new Date());

		List<GroupOfUser> groupsOfUser1 = new ArrayList<GroupOfUser>();
		groupsOfUser1.add(groupOfUserA);
		groupsOfUser1.add(groupOfUserB);
		List<GroupOfUser> groupsOfUser2 = new ArrayList<GroupOfUser>();
		groupsOfUser2.add(groupOfUserA);
		groupsOfUser2.add(groupOfUserC);
		List<GroupOfUser> groupsOfUser3 = new ArrayList<GroupOfUser>();
		groupsOfUser3.add(groupOfUserA);
		
		User user1 = User.builder()
						.userId(Long.valueOf(0))
						.userName("(ΦωΦ)FoFoFo")
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(true)
						.groups(groupsOfUser1)
						.address(udtValueAddress1)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();

		User user2 = User.builder()
						.userId(Long.valueOf(1))
						.userName("(・∀・)")
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(true)
						.address(udtValueAddress2)
						.groups(groupsOfUser2)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
	
		User user3 = User.builder()
						.userId(Long.valueOf(2))
						.userName("(・ω・`)")
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(false)
						.groups(groupsOfUser3)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();

		Address address1 = Address.builder()
									.addresspk(AddressPK.builder()
														.userId(Long.valueOf(0))
														.zipCd("100-0001")
														.build())
									.address("Tokyo")
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();

		Address address2 = Address.builder()
									.addresspk(AddressPK.builder()
														.userId(Long.valueOf(1))
														.zipCd("300-0001")
														.build())
									.address("Saitama")
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();

		Email email1 = Email.builder()
							.email("test@test.com")
							.userId(Long.valueOf(0))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build();
		
		Email email2 = Email.builder()
							.email("test2@test.com")
							.userId(Long.valueOf(0))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build();
		
		Email email3 = Email.builder()
							.email("test3@test.com")
							.userId(Long.valueOf(1))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build();

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		Credential credential1 = Credential.builder()
											.credentialPK(CredentialPK.builder()
															.credentialType("PASSWORD")
															.loginId("test")
															.build())
											.credentialKey("qwerty")
											.userId(Long.valueOf(0))
//											.expiredDate(DateFormat.getDateInstance().parse("2017/12/31"))
											.expiredDate(dateFormat.parse("2017/12/31"))
											.ver(0)
											.lastUpdatedDate(new Date())
											.build();
						
		Credential credential2 = Credential.builder()
											.credentialPK(CredentialPK.builder()
															.credentialType("PASSWORD")
															.loginId("test2")
															.build())
											.credentialKey("123456")
											.userId(Long.valueOf(1))
//											.expiredDate(DateFormat.getDateInstance().parse("2017/12/31"))
											.expiredDate(dateFormat.parse("2017/12/31"))
											.ver(0)
											.lastUpdatedDate(new Date())
											.build();
		
		UserOfGroup userOfGroup1 = new UserOfGroup();
		userOfGroup1.setUserId(Long.valueOf(0));
		userOfGroup1.setUserName("(ΦωΦ)FoFoFo");
		userOfGroup1.setVer(0);
		userOfGroup1.setLastUpdatedDate(new Date());

		UserOfGroup userOfGroup2 = new UserOfGroup();
		userOfGroup2.setUserId(Long.valueOf(1));
		userOfGroup2.setUserName("(・∀・)");
		userOfGroup2.setVer(0);
		userOfGroup2.setLastUpdatedDate(new Date());

		UserOfGroup userOfGroup3 = new UserOfGroup();
		userOfGroup3.setUserId(Long.valueOf(2));
		userOfGroup3.setUserName("(・ω・`)");
		userOfGroup3.setVer(0);
		userOfGroup3.setLastUpdatedDate(new Date());

		List<UserOfGroup> usersOfGroup1 = new ArrayList<UserOfGroup>();
		usersOfGroup1.add(userOfGroup1);
		usersOfGroup1.add(userOfGroup2);
		usersOfGroup1.add(userOfGroup3);
		
		List<UserOfGroup> usersOfGroup2 = new ArrayList<UserOfGroup>();
		usersOfGroup2.add(userOfGroup1);

		List<UserOfGroup> usersOfGroup3 = new ArrayList<UserOfGroup>();
		usersOfGroup3.add(userOfGroup2);

		Group groupA = Group.builder()
							.groupId(Long.valueOf(0))
							.groupName("GroupA")
							.ver(0)
							.lastUpdatedDate(new Date())
							.users(usersOfGroup1)
							.build();

		Group groupB = Group.builder()
							.groupId(Long.valueOf(1))
							.groupName("GroupB")
							.ver(0)
							.lastUpdatedDate(new Date())
							.users(usersOfGroup2)
							.build();

		Group groupC = Group.builder()
							.groupId(Long.valueOf(2))
							.groupName("GroupC")
							.ver(0)
							.lastUpdatedDate(new Date())
							.users(usersOfGroup3)
							.build();

		operations.insert(user1);
		operations.insert(user2);
		operations.insert(user3);
		operations.insert(address1);
		operations.insert(address2);
		operations.insert(email1);
		operations.insert(email2);
		operations.insert(email3);
		operations.insert(credential1);
		operations.insert(credential2);
		operations.insert(groupA);
		operations.insert(groupB);
		operations.insert(groupC);

	}

}
