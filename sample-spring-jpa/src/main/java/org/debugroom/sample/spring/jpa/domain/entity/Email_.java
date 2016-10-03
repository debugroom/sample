package org.debugroom.sample.spring.jpa.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-03T20:12:11.963+0900")
@StaticMetamodel(Email.class)
public class Email_ {
	public static volatile SingularAttribute<Email, EmailId> id;
	public static volatile SingularAttribute<Email, User> usr;
	public static volatile SingularAttribute<Email, String> email;
	public static volatile SingularAttribute<Email, Integer> ver;
	public static volatile SingularAttribute<Email, Date> lastUpdatedDate;
}
