package org.debugroom.sample.app.batch;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.support.PropertiesConverter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.debugroom.sample.domain.model.User;

/**
 * 
 * @author org.debugroom
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="testTransactionManager", defaultRollback = true)
@ContextConfiguration(locations 
		= "classpath*:/META-INF/spring/springBatch-sampleSimpleBatch-test.xml")
public class SampleSimpleBatchTest {

	private static final String DUSER_TABLE_INITIAL_FILE_PATH = "org/debugroom/sample/app/batch/sampleSimpleBatchTestDuserInitialData.xls";
	private static final String CREDENTIAL_TABLE_INITIAL_FILE_PATH = "org/debugroom/sample/app/batch/sampleSimpleBatchTestCredentialInitialData.xls";
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("jobSample")
	private Job job;
	
	@Autowired
	@Qualifier("testDataSource")
	private DataSource dataSource;
	
	private IDatabaseTester databaseTester;
	
	@Before
	public void setUp() throws Exception {
		databaseTester = new DataSourceDatabaseTester(dataSource){
			@Override public IDatabaseConnection getConnection() throws Exception{
				IDatabaseConnection databaseConnection = super.getConnection();
				databaseConnection.getConfig().setProperty(
						DatabaseConfig.PROPERTY_DATATYPE_FACTORY, 
						new PostgresqlDataTypeFactory());
				return databaseConnection;
			}
		};
		databaseTester.setDataSet(getDataSet(CREDENTIAL_TABLE_INITIAL_FILE_PATH));
		databaseTester.onSetup();
		databaseTester.setDataSet(getDataSet(DUSER_TABLE_INITIAL_FILE_PATH));
		databaseTester.onSetup();
	}
	
	protected IDataSet getDataSet(String path) throws Exception{
		URL url = SampleSimpleBatchTest.class.getClassLoader().getResource(path);
		InputStream inputStream = new BufferedInputStream(url.openStream());
		XlsDataSet dataset = new XlsDataSet(inputStream);
		return dataset;
	}

	@Test
	public void testJob() throws Exception{
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(job, builder.toJobParameters());
		assertThat(jobExecution.getExitStatus().getExitCode(), is("COMPLETED"));
		
	}

}
