package org.debugroom.sample.cassandra.app.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserResource<U, A> {

	private U user;
	private A address;
	
}
