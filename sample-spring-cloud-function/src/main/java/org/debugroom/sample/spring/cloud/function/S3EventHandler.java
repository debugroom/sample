package org.debugroom.sample.spring.cloud.function;

import com.amazonaws.services.s3.event.S3EventNotification;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class S3EventHandler extends SpringBootRequestHandler<S3EventNotification, String>{
}
