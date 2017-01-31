package db_pkg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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

public class Example5Read {
	static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient());

	static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	static String productCatalogTableName = "ProductCatalog";
	static String forumTableName = "Forum";
	static String threadTableName = "Thread";
	static String replyTableName = "Reply";

	public static void main(String[] args) throws Exception {

		readTables("Thread");
	}
	private static void readTables(String tableName) {
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider().getCredentials();
			AmazonDynamoDBClient client = new AmazonDynamoDBClient();

			// Set the endpoint URL// need to run locally
			client.withEndpoint("http://localhost:8000"); 	

			dynamoDB = new DynamoDB(client);
			Table table = dynamoDB.getTable("Thread");
			QuerySpec spec = new QuerySpec()
					.withKeyConditionExpression("ForumName = :v_name")
					.withValueMap(new ValueMap()
							.withString(":v_name", "Amazon S3455"));
			ItemCollection<QueryOutcome> items = table.query(spec);

			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.toJSONPretty());
			}

			// Query on reply
			table = dynamoDB.getTable("Reply");
			long twoWeeksAgoMilli = (new Date()).getTime() - (15L*24L*60L*60L*1000L);
			Date twoWeeksAgo = new Date();
			twoWeeksAgo.setTime(twoWeeksAgoMilli);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String twoWeeksAgoStr = df.format(twoWeeksAgo);
			spec = new QuerySpec()
					.withKeyConditionExpression("Id = :v_id and ReplyDateTime > :v_reply_dt_tm")
					.withFilterExpression("PostedBy = :v_posted_by")
					.withValueMap(new ValueMap()
							.withString(":v_id", "Amazon DynamoDB#DynamoDB Thread 1")
							.withString(":v_reply_dt_tm", twoWeeksAgoStr)
							.withString(":v_posted_by", "User B"))
					.withConsistentRead(true);
			items = table.query(spec);

			iterator = items.iterator();
			item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.toJSONPretty());
			}
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. " +
							"Please make sure that your credentials file is at the correct " +
							"location (~/.aws/credentials), and is in valid format.",
							e);
		}

	}



}
