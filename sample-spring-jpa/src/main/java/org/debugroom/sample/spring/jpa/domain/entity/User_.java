package org.debugroom.sample.spring.jpa.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-12T10:43:07.246+0900")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> userId;
	public static volatile SingularAttribute<User, Timestamp> lastUpdatedDate;
	public static volatile SingularAttribute<User, String> loginId;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, Integer> ver;
	public static volatile SingularAttribute<User, Address> address;
	public static volatile SetAttribute<User, Affiliation> affiliations;
	public static volatile SetAttribute<User, Email> emails;
}
