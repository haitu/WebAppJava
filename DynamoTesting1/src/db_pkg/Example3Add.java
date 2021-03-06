package db_pkg;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TimeZone;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class Example3Add {
	static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());
	static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	static String productCatalogTableName = "ProductCatalog";
	static String forumTableName = "Forum";
	static String threadTableName = "Thread";
	static String replyTableName = "Reply";

	public static void main(String[] args) throws Exception {

		addInTables();
	}

	private static void addInTables()
	{
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider().getCredentials();
			AmazonDynamoDBClient client = new AmazonDynamoDBClient();
			client.withEndpoint("http://localhost:8000"); 	
			dynamoDB = new DynamoDB(client);
			Table table = dynamoDB.getTable("Thread");
			Date date2 = new Date();
			long time2 = (new Date()).getTime() - (14 * 24 * 60 * 60 * 1000); // 14
			date2.setTime(time2);
			Item item = new Item()
					.withPrimaryKey("ForumName", "Amazon S3455")
					.withString("Subject", "S3 Thread 4")
					.withString("Message", "DynamoDB thread 1 message")
					.withString("LastPostedBy", "User H")
					.withString("LastPostedDateTime", dateFormatter.format(date2))
					.withNumber("Views", 0)
					.withNumber("Replies", 0)
					.withNumber("Answered", 0)
					.withStringSet( "Tags", new HashSet<String>(
							Arrays.asList("index", "primarykey", "table")));
			table.putItem(item);
			
			loadSampleThreads("Thread");
			System.err.println("Success.");     

		} catch (Exception e) {
			System.err.println("Program failed:");
			System.err.println(e.getMessage());
		}
	}
	
	private static void loadSampleThreads(String tableName) {
	     try {
	         long time1 = (new Date()).getTime() - (7 * 24 * 60 * 60 * 1000); // 7
	         // days
	         // ago
	         long time2 = (new Date()).getTime() - (14 * 24 * 60 * 60 * 1000); // 14
	         // days
	         // ago
	         long time3 = (new Date()).getTime() - (21 * 24 * 60 * 60 * 1000); // 21
	         // days
	         // ago

	         Date date1 = new Date();
	         date1.setTime(time1);

	         Date date2 = new Date();
	         date2.setTime(time2);

	         Date date3 = new Date();
	         date3.setTime(time3);

	         dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

	         Table table = dynamoDB.getTable(tableName);

	         System.out.println("Adding data to " + tableName);

	         Item item = new Item()
	             .withPrimaryKey("ForumName", "Amazon DynamoDB 3")
	             .withString("Subject", "DynamoDB Thread 1")
	             .withString("Message", "DynamoDB thread 1 message")
	             .withString("LastPostedBy", "User A")
	             .withString("LastPostedDateTime", dateFormatter.format(date2))
	             .withNumber("Views", 0)
	             .withNumber("Replies", 0)
	             .withNumber("Answered", 0)
	             .withStringSet( "Tags", new HashSet<String>(
	                 Arrays.asList("index", "primarykey", "table")));
	         table.putItem(item);

	         item = new Item()
	             .withPrimaryKey("ForumName", "Amazon DynamoDB")
	             .withString("Subject", "DynamoDB Thread 2")
	             .withString("Message", "DynamoDB thread 2 message")
	             .withString("LastPostedBy", "User A")
	             .withString("LastPostedDateTime", dateFormatter.format(date3))
	             .withNumber("Views", 0)
	             .withNumber("Replies", 0)
	             .withNumber("Answered", 0)
	             .withStringSet( "Tags", new HashSet<String>(
	                 Arrays.asList("index", "partitionkey", "sortkey")));
	         table.putItem(item);

	         item = new Item()
	             .withPrimaryKey("ForumName", "Amazon S3")
	             .withString("Subject", "S3 Thread 1")
	             .withString("Message", "S3 Thread 3 message")
	             .withString("LastPostedBy", "User A")
	             .withString("LastPostedDateTime", dateFormatter.format(date1))
	             .withNumber("Views", 0)
	             .withNumber("Replies", 0)
	             .withNumber("Answered", 0)
	             .withStringSet( "Tags", new HashSet<String>(
	                 Arrays.asList("largeobjects", "multipart upload")));
	         table.putItem(item);
	         
	         item = new Item()
	                 .withPrimaryKey("ForumName", "Amazon S3455")
	                 .withString("Subject", "S3 Thread 4")
	                 .withString("Message", "S3 Thread 4 message")
	                 .withString("LastPostedBy", "User B")
	                 .withString("LastPostedDateTime", dateFormatter.format(date1))
	                 .withNumber("Views", 0)
	                 .withNumber("Replies", 0)
	                 .withNumber("Answered", 0)
	                 .withStringSet( "Tags", new HashSet<String>(
	                     Arrays.asList("largeobjects1", "multipart upload1")));
	             table.putItem(item);

	     } catch (Exception e) {
	         System.err.println("Failed to create item in " + tableName);
	         System.err.println(e.getMessage());
	     }

	 }
}
