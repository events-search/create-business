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

	private Double average;
	
	private int noOfReviews;

	private List<Review> reviewList;

	
	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	@DynamoDBAttribute
	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	@DynamoDBAttribute
	public int getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}
	
}

