package com.event.business.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.event.business.model.Customer;

@Repository
public class CustomerRepository {

  @Autowired
	private DynamoDBMapper mapper;

	public Customer insertIntoDB(Customer customer) {
		mapper.save(customer);
		return customer;

	}

	public Customer updateIntoDB(Customer customer, String primaryKeyValue) {
		mapper.save(customer, EventUtil.getBuildExpression("CustomerId", primaryKeyValue));
	    return customer;
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

	public List<Customer> getObject(Customer customer) {
		List<Customer> result = mapper.scan(Customer.class, getCustomerScanner(customer));
		return result;
	}
	
	private DynamoDBScanExpression getCustomerScanner(Customer customer) {
		Map<String, Condition> filters = new HashMap<>();
		DynamoDBScanExpression scanner = new DynamoDBScanExpression();
		if (!StringUtils.isEmpty(customer.getUserName())) {
			filters.put("userName", getCondition(customer.getUserName()));
		}
		if (!StringUtils.isEmpty(customer.getPassword())) {
			filters.put("password", getCondition(customer.getPassword()));
		}	
		
		scanner.setScanFilter(filters);
		return scanner;
	}
	
	private Condition getCondition(String val) {
		Condition c = new Condition();
		c.withComparisonOperator(ComparisonOperator.EQ);
		c.withAttributeValueList(new AttributeValue().withS(val));
		return c;
	}



}
