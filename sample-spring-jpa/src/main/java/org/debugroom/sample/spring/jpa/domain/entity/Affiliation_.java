package org.debugroom.sample.spring.jpa.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-03T20:12:11.917+0900")
@StaticMetamodel(Affiliation.class)
public class Affiliation_ {
	public static volatile SingularAttribute<Affiliation, AffiliationId> id;
	public static volatile SingularAttribute<Affiliation, Group> grp;
	public static volatile SingularAttribute<Affiliation, User> usr;
	public static volatile SingularAttribute<Affiliation, Integer> ver;
	public static volatile SingularAttribute<Affiliation, Date> lastUpdatedDate;
}
