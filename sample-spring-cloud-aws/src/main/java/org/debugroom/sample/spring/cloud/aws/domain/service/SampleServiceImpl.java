package org.debugroom.sample.spring.cloud.aws.domain.service;

import javax.inject.Inject;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl {

	@Inject
	QueueMessagingTemplate queueMessagingTemplate;

}
