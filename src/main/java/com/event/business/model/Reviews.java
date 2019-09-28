package com.event.business.model;

import java.io.Serializable;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Reviews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String average;

	private List<Review> reviewList;

	@DynamoDBAttribute
	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}
	
	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

}
