package org.debugroom.sample.spring.websocket.app.web;

import org.debugroom.sample.spring.websocket.app.model.Message;
import org.debugroom.sample.spring.websocket.app.model.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	@MessageMapping("/endpoint")
	@SendTo("/topic/sample")
	public Notification getNotification(Message message){
		return Notification.builder()
				.title("(ΦωΦ)フフフ")
				.description(message.getMessageBody())
				.build();
	}
	
}
