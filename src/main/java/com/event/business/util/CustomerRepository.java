package com.event.business.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.event.business.model.Customer;

@Repository
public class CustomerRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public void insertIntoDB(Customer object) {
		mapper.save(object);

	}

	public void updateIntoDB(Customer e, String primaryKeyValue) {
		mapper.save(e, EventUtil.getBuildExpression("CustomerId", primaryKeyValue));
	}

	public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	public Customer getById(String id) {
		return mapper.load(Customer.class, id);
	}

	public List<Customer> getAll() {
		return mapper.scan(Customer.class, new DynamoDBScanExpression());
	}

	public PaginatedQueryList<Customer> search(String emailId) {
		Map<String, AttributeValue> expectedAttribute = new HashMap<>();
		expectedAttribute.put(":emailId", new AttributeValue(emailId));
		DynamoDBQueryExpression<Customer> dbQExp = new DynamoDBQueryExpression<>();
		dbQExp.setFilterExpression("emailId = :emailId");
		return this.mapper.query(Customer.class, dbQExp);
	}

}
