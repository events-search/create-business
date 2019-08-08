package com.event.business.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.event.business.model.LoginDetails;
import com.event.business.model.RetreiveBusinessDetails;

@Repository
public class DynamoDBRepository {
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDB(RetreiveBusinessDetails businessDetails) {
		mapper.save(businessDetails);
		
	}
	
	public RetreiveBusinessDetails getBusinessDetails(String businessId) {
		return mapper.load(RetreiveBusinessDetails.class, businessId);
	}
	
	public RetreiveBusinessDetails getBusinessDetailsByUser(String userName) {
		return mapper.load(RetreiveBusinessDetails.class, userName);
	}
	
	public boolean validateUser(String userName,String password) {
		LoginDetails loginDetails = mapper.load(LoginDetails.class, userName, password);
		if(userName.equals(loginDetails.getUserName())) {
			return true;
		}
		return false;
	}

	public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}
	
	

}
