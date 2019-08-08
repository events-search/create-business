package com.event.business.model;

import java.io.Serializable;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String serviceName;
	
	private Map<String,String> serviceDetails;

	@DynamoDBAttribute
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@DynamoDBAttribute
	public Map<String, String> getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(Map<String, String> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	
		
}
