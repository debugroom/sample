package org.debugroom.sample.spring.jpa.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-13T02:17:14.600+0900")
@StaticMetamodel(Affiliation.class)
public class Affiliation_ {
	public static volatile SingularAttribute<Affiliation, AffiliationPK> id;
	public static volatile SingularAttribute<Affiliation, Timestamp> lastUpdatedDate;
	public static volatile SingularAttribute<Affiliation, Integer> ver;
	public static volatile SingularAttribute<Affiliation, Group> grp;
	public static volatile SingularAttribute<Affiliation, User> usr;
}
