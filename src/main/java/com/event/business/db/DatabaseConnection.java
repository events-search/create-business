package com.event.business.db;

import java.util.Arrays;
import java.util.HashSet;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DatabaseConnection {

	private static void createItems() {

		// NOTE: We need to set your aws credentails to access the dynamoDB tables
		// Look for examples here:
		// https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/java/example_code/dynamodb/src/main/java/com/amazonaws/services/dynamodbv2/client/DynamoDBDynamicFaultInjection.java

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("BusinessDetails");
		try {

			Item item = new Item().withPrimaryKey("Id", 120).withString("Title", "Book 120 Title")
					.withString("ISBN", "120-1111111111")
					.withStringSet("Authors", new HashSet<String>(Arrays.asList("Author12", "Author22")))
					.withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
					.withBoolean("InPublication", false).withString("ProductCategory", "Book");
			table.putItem(item);

			item = new Item().withPrimaryKey("Id", 121).withString("Title", "Book 121 Title")
					.withString("ISBN", "121-1111111111")
					.withStringSet("Authors", new HashSet<String>(Arrays.asList("Author21", "Author 22")))
					.withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
					.withBoolean("InPublication", true).withString("ProductCategory", "Book");
			table.putItem(item);

		} catch (Exception e) {
			System.err.println("Create items failed.");
			System.err.println(e.getMessage());

		}
	}

}
