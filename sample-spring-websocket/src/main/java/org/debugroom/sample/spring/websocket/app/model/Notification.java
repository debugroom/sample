package org.debugroom.sample.spring.websocket.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Notification(){}
	
	private String title;
	private String description;

}
