package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Table("group")
public class Group {

	public Group(){
	}

	@PrimaryKey("group_id")
	private Long groupId;
	@Column("group_name")
	private String groupName;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;	
	@Column("users")
	private List<UserOfGroup> users;

}
