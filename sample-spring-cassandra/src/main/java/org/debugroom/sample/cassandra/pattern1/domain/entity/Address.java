package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Table("address")
public class Address {

	// constructor needs for avoiding repository instantiation error
	public Address(){
	}

	@PrimaryKey("addresspk")
	private AddressPK addresspk;
	@Column("address")
	private String address;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;	

}
