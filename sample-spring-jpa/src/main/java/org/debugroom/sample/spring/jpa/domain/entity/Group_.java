package org.debugroom.sample.spring.jpa.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-13T01:20:58.586+0900")
@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, String> groupId;
	public static volatile SingularAttribute<Group, String> groupName;
	public static volatile SingularAttribute<Group, Timestamp> lastUpdatedDate;
	public static volatile SingularAttribute<Group, Integer> ver;
	public static volatile SetAttribute<Group, Affiliation> affiliations;
}
