package org.debugroom.sample.spring.cloud.aws.domain.repository.dynamodb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class SampleRepositoryImpl {

	private static final String SAMPLE_TABLE_NAME = "sample";
	private static final String GLOBAL_SECONDARY_INDEX = "GLOBAL_SECONDARY_INDEX_CLOUMN";

	@Inject
	private DynamoDB dynamoDB;

	@Inject
	private AmazonDynamoDB amazonDynamoDB;

	public void initTable(){
		Table table = dynamoDB.getTable(SAMPLE_TABLE_NAME);
		DescribeTableResult describeTableResult = amazonDynamoDB.describeTable(SAMPLE_TABLE_NAME);
		if(describeTableResult != null && describeTableResult.getTable().getTableStatus().equals("ACTIVE")){
			table.delete();
			try {
				table.waitForDelete();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		GlobalSecondaryIndex globalSecondaryIndex = new GlobalSecondaryIndex()
				.withIndexName(GLOBAL_SECONDARY_INDEX)
				.withKeySchema(new KeySchemaElement("secondKey", KeyType.HASH), new KeySchemaElement("Date", KeyType.RANGE)) // HASH=partionKey, RANGE=sortKey
				.withProvisionedThroughput(new ProvisionedThroughput(5L, 5L))
				.withProjection(new Projection().withProjectionType(ProjectionType.ALL));

		List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("partitionId").withAttributeType(ScalarAttributeType.S));
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("secondKey").withAttributeType(ScalarAttributeType.S));
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("Date").withAttributeType(ScalarAttributeType.S));

		CreateTableRequest request = new CreateTableRequest()
				.withTableName(SAMPLE_TABLE_NAME)
				.withKeySchema(new KeySchemaElement().withAttributeName("partitionId").withKeyType(KeyType.HASH))
				.withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput()
						.withReadCapacityUnits(5L).withWriteCapacityUnits(10L))
				.withGlobalSecondaryIndexes(globalSecondaryIndex);

		table = dynamoDB.createTable(request);

		try {
			table.waitForActive();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



}
