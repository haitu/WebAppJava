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
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class Example4Delete {
	static DynamoDB dynamoDB;
	static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public static void main(String[] args) {
		DeleteThread();

	}
	private static void DeleteThread()
	{
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider().getCredentials();
			AmazonDynamoDBClient client = new AmazonDynamoDBClient();
			client.withEndpoint("http://localhost:8000"); 	
			dynamoDB = new DynamoDB(client);

			Table table = dynamoDB.getTable("Thread");
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
					.withPrimaryKey("ForumName", "Amazon S3455","Subject","S3 Thread 4")
					.withConditionExpression("#rp = :val")
					.withNameMap(new NameMap()
							.with("#rp", "Replies"))
					.withValueMap(new ValueMap()
							.withNumber(":val", 0))
					.withReturnValues(ReturnValue.ALL_OLD);

			DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
			System.err.println("Success.");     

		} catch (Exception e) {
			System.err.println("Program failed:");
			System.err.println(e.getMessage());
		}
	}
}
