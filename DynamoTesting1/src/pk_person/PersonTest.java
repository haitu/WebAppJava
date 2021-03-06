package pk_person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.LocalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public class PersonTest {

	DynamoDB dynamoDB;
	AWSCredentials credentials = null;
	AmazonDynamoDBClient client;
	
	public static void main(String[] args) {

		(new PersonTest()).test();
		
	}
	void init()
	{
		AWSCredentials credentials = null;
		client = new AmazonDynamoDBClient();
		// Set the endpoint URL// need to run locally
		client.withEndpoint("http://localhost:8000"); 	

		dynamoDB = new DynamoDB(client);
		credentials = new ProfileCredentialsProvider().getCredentials();
	}
	void test()
	{
		init();
		//createTable();
		addItems();
		readItem();
		System.out.println("Scan items-----------");
		scanItems();
	}
	void createTable()
	{
		
		
		
		try {
			
			createTable("Person", 10L, 5L, "PersonId", "N");
			
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. " +
							"Please make sure that your credentials file is at the correct " +
							"location (~/.aws/credentials), and is in valid format.",
							e);
		}
	}
	
	private void createTable(
			String tableName, long readCapacityUnits, long writeCapacityUnits, 
			String partitionKeyName, String partitionKeyType) {

		createTable(tableName, readCapacityUnits, writeCapacityUnits,
				partitionKeyName, partitionKeyType, null, null);
	}

	private void createTable(
			String tableName, long readCapacityUnits, long writeCapacityUnits, 
			String partitionKeyName, String partitionKeyType, 
			String sortKeyName, String sortKeyType) {

		try {

			ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement()
					.withAttributeName(partitionKeyName)
					.withKeyType(KeyType.HASH)); //Partition key

			ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition()
					.withAttributeName(partitionKeyName)
					.withAttributeType(partitionKeyType));

			if (sortKeyName != null) {
				keySchema.add(new KeySchemaElement()
						.withAttributeName(sortKeyName)
						.withKeyType(KeyType.RANGE)); //Sort key
				attributeDefinitions.add(new AttributeDefinition()
						.withAttributeName(sortKeyName)
						.withAttributeType(sortKeyType));
			}

			CreateTableRequest request = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(keySchema)
					.withProvisionedThroughput( new ProvisionedThroughput()
							.withReadCapacityUnits(readCapacityUnits)
							.withWriteCapacityUnits(writeCapacityUnits));

			

			request.setAttributeDefinitions(attributeDefinitions);

			System.out.println("Issuing CreateTable request for " + tableName);
			Table table = dynamoDB.createTable(request);
			System.out.println("Waiting for " + tableName
					+ " to be created...this may take a while...");
			table.waitForActive();

		} catch (Exception e) {
			System.err.println("CreateTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}
	
	void addItems()
	{
		try
		{
			Table table = dynamoDB.getTable("Person");
			Item item = new Item().withPrimaryKey("PersonId", 1)
					.withString("FirstName", "Hai")
					.withString("LastName", "Tu")
					.withNumber("Age", 37);
			table.putItem(item);

			item = new Item().withPrimaryKey("PersonId", 2)
					.withString("FirstName", "Phuong")
					.withString("LastName", "Tran")
					.withNumber("Age", 34);
			
			item = new Item().withPrimaryKey("PersonId", 3)
					.withString("FirstName", "Bao")
					.withString("LastName", "Tu")
					.withString("MiddleName", "Minh")
					.withNumber("Age", 34);
			
			table.putItem(item);
			
		}catch (Exception e) {
			System.err.println("Failed to add item in " + "Person");
			System.err.println(e.getMessage());
		}
		
	}
	void readItem()
	{
		Table table = dynamoDB.getTable("Person");
		QuerySpec spec = new QuerySpec()
				.withKeyConditionExpression("PersonId = :v_id")
				.withValueMap(new ValueMap()
						.withNumber(":v_id", 1));
		ItemCollection<QueryOutcome> items = table.query(spec);

		Iterator<Item> iterator = items.iterator();
		Item item = null;
		while (iterator.hasNext()) {
			item = iterator.next();
			System.out.println(item.toJSONPretty());
		}
	}
	void scanItems()
	{
		
		ScanRequest scanRequest = new ScanRequest()
			    .withTableName("Person");

			ScanResult result = client.scan(scanRequest);
			for (Map<String, AttributeValue> item : result.getItems()){
				//System.out.println(item.values());
				//printItem(item.values());
			}
			
			
			Table table = dynamoDB.getTable("Person");
			Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
	        expressionAttributeValues.put(":id", 1);
	        
	        ItemCollection<ScanOutcome> items = table.scan(
	            "PersonId >= :id", //FilterExpression
	            "PersonId, FirstName, LastName,MiddleName, Age", //ProjectionExpression
	            null, //ExpressionAttributeNames - not used in this example 
	            expressionAttributeValues);
	        
	        //System.out.println("Scan of " + tableName + " for items with a price less than 100.");
	        Iterator<Item> iterator = items.iterator();
	        while (iterator.hasNext()) {
	        	Item it1 = iterator.next();
	            System.out.println(it1.toJSONPretty());
	            //System.out.println(it1.get("PersonId"));//this can get each of value of attribute
	        }    
	}
	

}
