package org.debugroom.sample.spring.cloud.aws.config;

import org.debugroom.sample.spring.cloud.aws.app.listener.QueueListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SqsConfig {

	@Value("${queue.endpoint}")
	private String queueEndpoint;
	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    
    @Bean
    public QueueListener queueListener(){
    	return new QueueListener();
    }
    
    @Bean
    public AmazonSQSAsync amazonSQSClient(){
    	if(System.getProperty("aws.accessKeyId") == null ||
    			System.getProperty("aws.accessKeyId").equals("")){
    		System.setProperty("aws.accessKeyId", accessKey);
    	}
    	if(System.getProperty("aws.secretKeyId") == null ||
    			System.getProperty("aws.secretKeyId").equals("")){
    		System.setProperty("aws.secretKey", secretKey);
    	}
    	return AmazonSQSAsyncClientBuilder
    			.standard()
    			.withEndpointConfiguration(new EndpointConfiguration(
    					queueEndpoint, region))
    			.withCredentials(new SystemPropertiesCredentialsProvider())
    			.build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSClient());
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer msgListenerContainer = simpleMessageListenerContainerFactory().createSimpleMessageListenerContainer();
        msgListenerContainer.setMessageHandler(queueMessageHandler());
        return msgListenerContainer;
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
        SimpleMessageListenerContainerFactory msgListenerContainerFactory = new SimpleMessageListenerContainerFactory();
        msgListenerContainerFactory.setAmazonSqs(amazonSQSClient());
        return msgListenerContainerFactory;
    }

    @Bean
    public QueueMessageHandler queueMessageHandler() {
    	QueueMessageHandlerFactory queueMsgHandlerFactory = new QueueMessageHandlerFactory();
        queueMsgHandlerFactory.setAmazonSqs(amazonSQSClient());
        QueueMessageHandler queueMessageHandler = queueMsgHandlerFactory.createQueueMessageHandler();
        return queueMessageHandler;
    }

}
