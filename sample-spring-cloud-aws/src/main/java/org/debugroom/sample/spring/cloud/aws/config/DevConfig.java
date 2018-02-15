package org.debugroom.sample.spring.cloud.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Profile("dev")
@PropertySource(value = {
		"file:C:\\work\\credential.properties"
})
@Configuration
public class DevConfig {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).build();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDBClient(){
    	return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.fromName(region)).build();
    }

    @Bean
    public DynamoDB dynamoDB(){
    	return new DynamoDB(amazonDynamoDBClient());
    }

    @Bean
    public AmazonSQSAsync amazonSQSClient(){
    	return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.fromName(region)).build();
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
