package com.event.business.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Rating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double averageRating;
	
	private int noOfReviews;

	
	
	@DynamoDBAttribute
	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	@DynamoDBAttribute
	public int getNoOfReviews() {
		return noOfReviews;
	}

	
	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}
	
}

