package com.event.business.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.event.business.model.LoginDetails;

public class EventUtil {
	
	public static <E> Collection<E> makeCollection(Iterable<E> iter) {
	    Collection<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
	
	public static DynamoDBSaveExpression getBuildExpression(String pk, String pkValue){
    	DynamoDBSaveExpression expression = new DynamoDBSaveExpression();
    	Map<String, ExpectedAttributeValue> expectedValue=new HashMap<>();
    	expectedValue.put(pk, new ExpectedAttributeValue(new AttributeValue(pkValue)).withComparisonOperator(ComparisonOperator.EQ));
    	expression.setExpected(expectedValue);   
    	return expression;
    }
	
	public static boolean validateLoginDetails(LoginDetails loginDetails) {
		if (StringUtils.isEmpty(loginDetails.getUserName()) || StringUtils.isEmpty(loginDetails.getPassword())) {
			return false;
		}
		return true;
	}

	
}
