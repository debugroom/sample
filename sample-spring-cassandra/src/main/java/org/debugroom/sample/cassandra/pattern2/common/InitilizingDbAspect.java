package org.debugroom.sample.cassandra.pattern2.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Affiliation;
import org.debugroom.sample.cassandra.pattern2.domain.entity.AffiliationPK;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class InitilizingDbAspect {

	@Autowired CassandraOperations operations;
	
	@Pointcut("execution(* org.debugroom.sample.cassandra.domain.service.*.*(..))")
	public void pointcut(){
	}
	
	@Before("pointcut()")
	public void before() throws ParseException {
		
		log.info(this.getClass().getName() + "#before() executed");

		operations.truncate("affiliation");
		operations.truncate("group");
		operations.truncate("users");

		List<Email> emails1 = new ArrayList<Email>();
		List<Email> emails2 = new ArrayList<Email>();
		emails1.add(Email.builder()
						.email("test@test.com")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build());
		emails1.add(Email.builder()
						.email("test2@test.com")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build());
		emails1.add(Email.builder()
						.email("test3@test.com")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build());
		emails1.add(Email.builder()
						.email("test4@test.com")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build());
		List<Credential> credentials1 = new ArrayList<Credential>();
		List<Credential> credentials2 = new ArrayList<Credential>();
		credentials1.add(Credential.builder()
							.credentialType("password")
							.credentialKey("123456")
							.expiredDate(DateFormat.getDateInstance().parse("2016/12/31"))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build());
		credentials1.add(Credential.builder()
							.credentialType("password")
							.credentialKey("qwerty")
							.expiredDate(DateFormat.getDateInstance().parse("9999/12/31"))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build());
		credentials2.add(Credential.builder()
							.credentialType("password")
							.credentialKey("asdfgh")
							.expiredDate(DateFormat.getDateInstance().parse("9999/12/31"))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build());
		credentials2.add(Credential.builder()
							.credentialType("oauth2")
							.credentialKey("1234567890qwertyuiopasdfghjklzxcvbnm")
							.expiredDate(DateFormat.getDateInstance().parse("9999/12/31"))
							.ver(0)
							.lastUpdatedDate(new Date())
							.build());
		
		User user1 = User.builder()
						.userId(Long.valueOf(0))
						.userName("(ΦωΦ)FoFoFo")
						.loginId("test")
						.credentials(credentials1)
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(true)
						.address(Address.builder()
										.zipCd("100-0001")
										.address("Tokyo")
										.ver(0)
										.lastUpdatedDate(new Date())
										.build())
						.emails(emails1)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		User user2 = User.builder()
						.userId(Long.valueOf(1))
						.userName("(・∀・)")
						.loginId("test2")
						.credentials(credentials2)
						.isEnabled(true)
						.isLocked(false)
						.isAdmin(true)
						.address(Address.builder()
										.zipCd("851-0000")
										.address("Nagasaki")
										.ver(0)
										.lastUpdatedDate(new Date())
										.build())
						.emails(emails2)
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();		
		Group groupA = Group.builder()
						.groupId(Long.valueOf(0))
						.groupName("GroupA")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		Group groupB = Group.builder()
						.groupId(Long.valueOf(1))
						.groupName("GroupB")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		Group groupC = Group.builder()
						.groupId(Long.valueOf(2))
						.groupName("GroupC")
						.ver(0)
						.lastUpdatedDate(new Date())
						.build();
		
		Affiliation affiliation1 = Affiliation.builder()
									.affiliationPK(AffiliationPK.builder()
																.userId(Long.valueOf(0))
																.groupId(Long.valueOf(0))
																.build())
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();
		Affiliation affiliation2 = Affiliation.builder()
									.affiliationPK(AffiliationPK.builder()
																.userId(Long.valueOf(0))
																.groupId(Long.valueOf(1))
																.build())
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();
		Affiliation affiliation3 = Affiliation.builder()
									.affiliationPK(AffiliationPK.builder()
																.userId(Long.valueOf(1))
																.groupId(Long.valueOf(1))
																.build())
									.ver(0)
									.lastUpdatedDate(new Date())
									.build();

		operations.insert(user1);
		operations.insert(user2);
		operations.insert(groupA);
		operations.insert(groupB);
		operations.insert(groupC);
		operations.insert(affiliation1);
		operations.insert(affiliation2);
		operations.insert(affiliation3);

	}

}
