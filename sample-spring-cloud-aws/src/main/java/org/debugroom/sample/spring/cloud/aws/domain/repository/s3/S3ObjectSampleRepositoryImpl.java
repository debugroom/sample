package org.debugroom.sample.spring.cloud.aws.domain.repository.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.debugroom.sample.spring.cloud.aws.domain.model.SampleObject;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;

public class S3ObjectSampleRepositoryImpl {

	private static final String BUCKET_NAME = "data.debugroom.org";
	private static final String OBJECT_KEY = "sample/spring-cloud-aws/sample.csv";

	@Inject
	AmazonS3 amazonS3;

	public List<SampleObject> getSamples(){

		S3Object s3object = amazonS3.getObject(BUCKET_NAME, OBJECT_KEY);
		List<SampleObject> sampleObjects = new ArrayList<SampleObject>();

		if(s3object != null){

			BufferedReader bufferedReader = null;

			bufferedReader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));

			String line;
			try {
				while((line = bufferedReader.readLine()) != null){

					String[] dataAttributes = line.split(",");
					sampleObjects.add(SampleObject.builder()
							.partionKey(dataAttributes[0])
							.secondKey(dataAttributes[1])
							.date(dataAttributes[2])
							.build());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sampleObjects;
	}

}
