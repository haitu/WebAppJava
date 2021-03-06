package db_pkg;

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
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.LocalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class Example2Update {


	static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
 

 static SimpleDateFormat dateFormatter = new SimpleDateFormat(
         "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

 static String productCatalogTableName = "ProductCatalog";
 static String forumTableName = "Forum";
 static String threadTableName = "Thread";
 static String replyTableName = "Reply";

 public static void main(String[] args) throws Exception {

	 updateTable("Thread");
 }
 private static void updateTable(String tableName) {
	 AWSCredentials credentials = null;
     try {
    	 credentials = new ProfileCredentialsProvider().getCredentials();
    	 AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.withEndpoint("http://localhost:8000"); 	
		dynamoDB = new DynamoDB(client);
		Map<String, String> expressionAttributeNames = new HashMap<String, String>();
        expressionAttributeNames.put("#na", "NewAttribute");

        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
        .withPrimaryKey("ForumName", "Amazon S3","Subject","S3 Thread 1")
        .withUpdateExpression("set #na = :val1")
        .withNameMap(new NameMap()
            .with("#na", "Message"))
        .withValueMap(new ValueMap()
            .withString(":val1", "Some value"))
        .withReturnValues(ReturnValue.ALL_NEW);

        Table table = dynamoDB.getTable(tableName);
        UpdateItemOutcome outcome =  table.updateItem(updateItemSpec);

        // Check the response.
        System.out.println("Printing item after adding new attribute...");
        System.out.println(outcome.getItem().toJSONPretty());    
	

     } catch (Exception e) {
         System.err.println("Program failed:");
         System.err.println(e.getMessage());
     }
 
 }

}
