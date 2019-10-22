package com.event.business.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "BusinessDetails")
public class BusinessDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String businessId;
	@NotNull(message = "businessName is mandatory")
	private String businessName;
	@NotNull(message = "businessType is mandatory")
	private String businessType;

	@NotNull(message = "primaryPhoneNumber is mandatory")
	private String primaryPhoneNumber;
	
	private String primaryExtension;	
	
	private String secondaryPhoneNumber;
	
	private String thirdPhoneNumber;
	
	private String secondaryExtension;
	
	private String thirdExtension;

	@NotNull(message = "primaryPhoneType is mandatory")	
	private String primaryPhoneType;
	
	private String secondaryPhoneType;
	
	private String thirdPhoneType;

	
	@NotNull(message = "UserName is mandatory")
	private String userName;

	@NotNull(message = "FirstName is mandatory")
	private String firstName;

	@NotNull(message = "lastName is mandatory")
	private String lastName;

	@NotNull(message = "email is mandatory")
	@Email
	private String email;

	private String websiteUrl;

	private String businessEstDate;

	private TransactionDetails transaction;

	private String businessDescription;

	private List<String> servicesProvided;

	@NotNull(message = "password is mandatory")
	private String password;

	private String primaryAddressType;

	@NotNull(message = "primaryAddressLine1 is mandatory")
	private String primaryAddressLine1;

	private String primaryAddressLine2;

	@NotNull(message = "primaryCity is mandatory")
	private String primaryCity;

	@NotNull(message = "primaryState is mandatory")
	private String primaryState;

	@NotNull(message = "primaryZipCode is mandatory")
	private String primaryZipCode;

	private String primaryLandmark;

	@NotNull(message = "primaryCountry is mandatory")
	private String primaryCountry;
	
	private String secondaryAddressLine1;

	private String secondaryAddressLine2;

	private String secondaryCity;

	private String secondaryState;

	private String secondaryZipCode;

	private String secondaryLandmark;

	private String secondaryCountry;
	
	private Rating rating;

	private String secondaryAddressType;
	
	private String thirdAddressType;
	
	@DynamoDBHashKey(attributeName = "businessId")
	@DynamoDBAutoGeneratedKey
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@DynamoDBAttribute
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@DynamoDBAttribute
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	@DynamoDBAttribute
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}

	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	
	@DynamoDBAttribute
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@DynamoDBAttribute
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBAttribute
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	@DynamoDBAttribute
	public String getBusinessEstDate() {
		return businessEstDate;
	}

	public void setBusinessEstDate(String businessEstDate) {
		this.businessEstDate = businessEstDate;
	}

	@DynamoDBAttribute
	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	@DynamoDBAttribute
	public List<String> getServicesProvided() {
		return servicesProvided;
	}

	public void setServicesProvided(List<String> servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	@DynamoDBAttribute
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TransactionDetails getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionDetails transaction) {
		this.transaction = transaction;
	}

	@DynamoDBAttribute
	public String getSecondaryAddressLine1() {
		return secondaryAddressLine1;
	}

	@DynamoDBAttribute
	public String getPrimaryAddressType() {
		return primaryAddressType;
	}

	public void setPrimaryAddressType(String primaryAddressType) {
		this.primaryAddressType = primaryAddressType;
	}

	@DynamoDBAttribute
	public String getThirdPhoneNumber() {
		return thirdPhoneNumber;
	}

	public void setThirdPhoneNumber(String thirdPhoneNumber) {
		this.thirdPhoneNumber = thirdPhoneNumber;
	}

	@DynamoDBAttribute
	public String getPrimaryPhoneType() {
		return primaryPhoneType;
	}

	public void setPrimaryPhoneType(String primaryPhoneType) {
		this.primaryPhoneType = primaryPhoneType;
	}

	@DynamoDBAttribute
	public String getSecondaryPhoneType() {
		return secondaryPhoneType;
	}

	public void setSecondaryPhoneType(String secondaryPhoneType) {
		this.secondaryPhoneType = secondaryPhoneType;
	}

	@DynamoDBAttribute
	public String getThirdPhoneType() {
		return thirdPhoneType;
	}

	public void setThirdPhoneType(String thirdPhoneType) {
		this.thirdPhoneType = thirdPhoneType;
	}

	@DynamoDBAttribute
	public String getPrimaryAddressLine1() {
		return primaryAddressLine1;
	}

	public void setPrimaryAddressLine1(String primaryAddressLine1) {
		this.primaryAddressLine1 = primaryAddressLine1;
	}

	@DynamoDBAttribute
	public String getPrimaryAddressLine2() {
		return primaryAddressLine2;
	}

	public void setPrimaryAddressLine2(String primaryAddressLine2) {
		this.primaryAddressLine2 = primaryAddressLine2;
	}

	@DynamoDBAttribute
	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	@DynamoDBAttribute
	public String getPrimaryState() {
		return primaryState;
	}

	public void setPrimaryState(String primaryState) {
		this.primaryState = primaryState;
	}

	@DynamoDBAttribute
	public String getPrimaryZipCode() {
		return primaryZipCode;
	}

	public void setPrimaryZipCode(String primaryZipCode) {
		this.primaryZipCode = primaryZipCode;
	}

	@DynamoDBAttribute
	public String getPrimaryLandmark() {
		return primaryLandmark;
	}

	public void setPrimaryLandmark(String primaryLandmark) {
		this.primaryLandmark = primaryLandmark;
	}

	@DynamoDBAttribute
	public String getPrimaryCountry() {
		return primaryCountry;
	}
	
	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}

	public void setSecondaryAddressLine1(String secondaryAddressLine1) {
		this.secondaryAddressLine1 = secondaryAddressLine1;
	}

	@DynamoDBAttribute
	public String getSecondaryAddressLine2() {
		return secondaryAddressLine2;
	}

	public void setSecondaryAddressLine2(String secondaryAddressLine2) {
		this.secondaryAddressLine2 = secondaryAddressLine2;
	}

	@DynamoDBAttribute
	public String getSecondaryCity() {
		return secondaryCity;
	}

	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}

	@DynamoDBAttribute
	public String getSecondaryState() {
		return secondaryState;
	}

	public void setSecondaryState(String secondaryState) {
		this.secondaryState = secondaryState;
	}

	@DynamoDBAttribute
	public String getSecondaryZipCode() {
		return secondaryZipCode;
	}

	public void setSecondaryZipCode(String secondaryZipCode) {
		this.secondaryZipCode = secondaryZipCode;
	}

	@DynamoDBAttribute
	public String getSecondaryLandmark() {
		return secondaryLandmark;
	}

	public void setSecondaryLandmark(String secondaryLandmark) {
		this.secondaryLandmark = secondaryLandmark;
	}

	@DynamoDBAttribute
	public String getSecondaryCountry() {
		return secondaryCountry;
	}

	public void setSecondaryCountry(String secondaryCountry) {
		this.secondaryCountry = secondaryCountry;
	}

	@DynamoDBAttribute
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}

	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}


	@DynamoDBAttribute	
	public String getPrimaryExtension() {
		return primaryExtension;
	}

	public void setPrimaryExtension(String primaryExtension) {
		this.primaryExtension = primaryExtension;
	}

	@DynamoDBAttribute
	public String getSecondaryExtension() {
		return secondaryExtension;
	}

	public void setSecondaryExtension(String secondaryExtension) {
		this.secondaryExtension = secondaryExtension;
	}

	@DynamoDBAttribute
	public String getThirdExtension() {
		return thirdExtension;
	}

	public void setThirdExtension(String thirdExtension) {
		this.thirdExtension = thirdExtension;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

  @DynamoDBAttribute
	public String getSecondaryAddressType() {
		return secondaryAddressType;
	}

	public void setSecondaryAddressType(String secondaryAddressType) {
		this.secondaryAddressType = secondaryAddressType;
	}

	@DynamoDBAttribute
	public String getThirdAddressType() {
		return thirdAddressType;
	}

	public void setThirdAddressType(String thirdAddressType) {
		this.thirdAddressType = thirdAddressType;
	}
	
}
