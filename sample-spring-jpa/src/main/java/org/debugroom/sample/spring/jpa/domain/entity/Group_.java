package org.debugroom.sample.spring.jpa.domain.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-03T20:11:45.880+0900")
@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, String> groupId;
	public static volatile SingularAttribute<Group, String> groupName;
	public static volatile SingularAttribute<Group, Integer> ver;
	public static volatile SingularAttribute<Group, Date> lastUpdatedDate;
	public static volatile SetAttribute<Group, Affiliation> affiliations;
}
