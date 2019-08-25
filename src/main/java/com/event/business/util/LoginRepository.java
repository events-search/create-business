package com.event.business.util;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.event.business.model.LoginDetails;

@Repository
public class LoginRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public LoginDetails insertIntoDB(LoginDetails object) {
		mapper.save(object);
		return object;

	}

	public LoginDetails updateIntoDB(LoginDetails e, String primaryKeyValue) {
		mapper.save(e, EventUtil.getBuildExpression("loginDetailsId", primaryKeyValue));
		return e;
	}

	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDB(LoginDetails object) {
		mapper.save(object);
		
	}
	
	public void updateIntoDB(LoginDetails e, String primaryKeyValue) {
		mapper.save(e, getBuildExpression("loginDetailsId", primaryKeyValue));
	}
	
	
	public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	public LoginDetails getById(String id) {
		return mapper.load(LoginDetails.class, id);
	}

	public List<LoginDetails> getAll() {
		return mapper.scan(LoginDetails.class, new DynamoDBScanExpression());
	}

}
