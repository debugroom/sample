package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import com.datastax.driver.core.DataType.Name;
import com.datastax.driver.core.UDTValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@Table("users")
public class User{

	public User(){
	}
	@PrimaryKey("user_id")
	private Long userId;
	@Column("user_name")
	private String userName;
	@Column("is_enabled")
	private boolean isEnabled;
	@Column("is_locked")
	private boolean isLocked;
	@Column("is_admin")
	private boolean isAdmin;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;	
//	@Column("current")
//    private AddressOfUser current;
//	@Transient
	@CassandraType(type = Name.UDT, userTypeName="addressofuser")
	private UDTValue address;
	@Transient
	List<Credential> credentials;
	@Transient
	List<Email> emails;
	@Column("groups")
	List<GroupOfUser> groups;

}