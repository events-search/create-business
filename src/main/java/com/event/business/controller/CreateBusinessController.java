package com.event.business.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.BusinessDetails;
import com.event.business.util.BusinessRepository;
import com.event.business.util.MyBeanUtils;

@RestController
public class CreateBusinessController {

	@Autowired
	private BusinessRepository repository;

	@PostMapping(path = "/business")
	public ResponseEntity<BusinessDetails> persistBusiness(@Valid @RequestBody BusinessDetails businessDetails) {
		return new ResponseEntity<>(repository.insertIntoDB(businessDetails), HttpStatus.CREATED);
	}

	@PutMapping(path = "/business")
	public ResponseEntity<BusinessDetails> updateBusiness(@RequestBody BusinessDetails businessDetails) {
		if (!StringUtils.isEmpty(businessDetails.getBusinessId())) {
			return new ResponseEntity<>(
					repository.updateIntoDB(getUpdatedBusiness(businessDetails), businessDetails.getBusinessId()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	private BusinessDetails getUpdatedBusiness(BusinessDetails businessDetails) {
		BusinessDetails businessDetailsObj = repository.getById(businessDetails.getBusinessId());
		try {
			MyBeanUtils.copyPropertiesNotNull(businessDetailsObj, businessDetails);
		} catch (InvocationTargetException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return businessDetailsObj;
	}

	@GetMapping(path = "/business/{business_id}")
	public ResponseEntity<BusinessDetails> getBusinessById(
			@PathVariable(value = "business_id", required = true) String businessId) {
		return new ResponseEntity<>(repository.getById(businessId), HttpStatus.OK);
	}

	@GetMapping(path = "/business")
	public ResponseEntity<List<BusinessDetails>> getBusiness() {
		return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "/business/search")
	public ResponseEntity<List<BusinessDetails>> getSearchBusiness(@RequestBody BusinessDetails businessDetails) {
		return new ResponseEntity<>(repository.getObject(businessDetails), HttpStatus.OK);

	}

	@GetMapping(path = "/business/search/{user_name}")
	public ResponseEntity<BusinessDetails> getSearchBusinessDetailsByUserName(
			@PathVariable(value = "user_name", required = true) String userName) throws Exception {
		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setUserName(userName);
		List<BusinessDetails> businessDetailsList = repository.getObject(businessDetails);
		if (CollectionUtils.isEmpty(businessDetailsList)) {
			throw new Exception("no BusinessDetails exists with username: " + userName);
		} else if (businessDetailsList.size() > 1) {
			throw new Exception("multiple BusinessDetails exists with username: " + userName);
		}
		return new ResponseEntity<>(businessDetailsList.get(0), HttpStatus.OK);
	}

}
