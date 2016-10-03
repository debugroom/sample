package org.debugroom.sample.spring.jpa.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-03T20:12:11.910+0900")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, String> userId;
	public static volatile SingularAttribute<Address, User> usr;
	public static volatile SingularAttribute<Address, String> zipCd;
	public static volatile SingularAttribute<Address, String> address;
	public static volatile SingularAttribute<Address, Integer> ver;
	public static volatile SingularAttribute<Address, Date> lastUpdatedDate;
}
