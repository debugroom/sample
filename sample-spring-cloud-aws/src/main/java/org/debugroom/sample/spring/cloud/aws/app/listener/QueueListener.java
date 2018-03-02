package org.debugroom.sample.spring.cloud.aws.app.listener;

import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableSqs
@Component
public class QueueListener {

	@SqsListener(value = "SampleNotify", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onMessage(String message) throws Exception{
		log.info(message);
    }

}
