package org.debugroom.sample.cassandra.app.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InputParam {

	public InputParam(){}
	private Long userId;
	private String userName;
	private String zipCd;
	private String address;
	private String email;
	private String newEmail;
	private Long groupId;
	private String groupName;

}
