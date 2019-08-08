package com.event.business.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamDBConfig {
    @Value("${aws.access.key}")
	private String awsAccessKey;

    @Value("${aws.secret.key}")
	private String awsSecretKey;

    @Value("${aws.region}")
	private String awsRegion;

    @Value("${aws.end.point}")
	private String awsEndPoint;
    
    @Bean
    public DynamoDBMapper mapper() {
    	return new DynamoDBMapper(getAwsDBConfig());
    }
    
    public AmazonDynamoDB getAwsDBConfig() {
    	return AmazonDynamoDBClientBuilder.standard()
    			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndPoint, awsRegion))
    			//.withRegion(Regions.US_EAST_2)
    			 .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
    			 .build();
    }


}
