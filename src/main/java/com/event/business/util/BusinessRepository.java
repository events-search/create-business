package com.event.business.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.event.business.model.BusinessDetails;

@Repository
public class BusinessRepository{
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDB(BusinessDetails object) {
		mapper.save(object);
		
	}
	
	public void updateIntoDB(BusinessDetails e, String primaryKeyValue) {
		mapper.save(e, EventUtil.getBuildExpression("businessId", primaryKeyValue));
	}
	
	public BusinessDetails getById(String id) {
	  return mapper.load(BusinessDetails.class, id);
	}	
	
	public List<BusinessDetails> getAll() {
		return mapper.scan(BusinessDetails.class, new DynamoDBScanExpression());
	}
	
	public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}
	
}
