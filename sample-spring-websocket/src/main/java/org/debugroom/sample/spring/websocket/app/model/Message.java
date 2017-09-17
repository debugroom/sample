package org.debugroom.sample.spring.websocket.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Message implements Serializable{
	
	private static final long serialVersionUID = 3183888589644031401L;

	public Message(){}
	
	private String messageBody;
	
}
