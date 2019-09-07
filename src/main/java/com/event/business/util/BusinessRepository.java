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
import com.event.business.model.BusinessDetails;

@Repository
public class BusinessRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public BusinessDetails insertIntoDB(BusinessDetails businessDetails) {
		mapper.save(businessDetails);
		return businessDetails;

	}

	public BusinessDetails updateIntoDB(BusinessDetails businessDetails, String primaryKeyValue) {
		mapper.save(businessDetails, EventUtil.getBuildExpression("businessId", primaryKeyValue));
		return businessDetails;
	}

	public BusinessDetails getById(String id) {
		return mapper.load(BusinessDetails.class, id);
	}

	public List<BusinessDetails> getAll() {
		return mapper.scan(BusinessDetails.class, new DynamoDBScanExpression());
	}

	public List<BusinessDetails> getObject(BusinessDetails businessDetails) {

		return mapper.scan(BusinessDetails.class, getBusinessScanner(businessDetails));
	}

	private DynamoDBScanExpression getBusinessScanner(BusinessDetails businessDetails) {
		Map<String, Condition> filters = new HashMap<>();
		DynamoDBScanExpression scanner = new DynamoDBScanExpression();
		if (!StringUtils.isEmpty(businessDetails.getBusinessName())) {
			filters.put("businessName", getCondition(businessDetails.getBusinessName()));
		}
		if (!StringUtils.isEmpty(businessDetails.getBusinessType())) {
			filters.put("businessType", getCondition(businessDetails.getBusinessType()));
		}
		if (!StringUtils.isEmpty(businessDetails.getCity())) {
			filters.put("city", getCondition(businessDetails.getCity()));
		}
		if (!StringUtils.isEmpty(businessDetails.getState())) {
			filters.put("state", getCondition(businessDetails.getState()));
		}
		if (!StringUtils.isEmpty(businessDetails.getZipCode())) {
			filters.put("zipCode", getCondition(businessDetails.getZipCode()));
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

  public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}
}
