package org.debugroom.sample.jbatch.app.batch.launcher;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

public class SampleBatchletLauncher {

	public static void main(String[] args){
		
		Properties properties = new Properties();
		properties.setProperty("param", "param1");
		
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long executionId = jobOperator.start("sampleBatchlet", properties);
		JobExecution jobExecution = jobOperator.getJobExecution(executionId);
		BatchStatus status = jobExecution.getBatchStatus();
		System.out.println(status.toString());

	}

}
