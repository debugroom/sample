package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import com.datastax.driver.core.DataType.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@UserDefinedType
public class AddressOfUser {

	// AddressOfUser must have single column mapping
	@Column("zip_cd")
	private String zipCd;
	@Column("address")
	private String address;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;	

}
