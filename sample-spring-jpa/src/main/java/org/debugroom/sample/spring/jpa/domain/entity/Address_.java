package org.debugroom.sample.spring.jpa.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-13T01:29:52.362+0900")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, String> userId;
	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, Timestamp> lastUpdatedDate;
	public static volatile SingularAttribute<Address, Integer> ver;
	public static volatile SingularAttribute<Address, String> zipCd;
	public static volatile SingularAttribute<Address, User> usr;
}
