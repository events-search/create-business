package com.event.business.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.event.business.model.LoginDetails;
import com.event.business.util.BusinessRepository;
import com.event.business.util.EventUtil;
import com.event.business.util.MyBeanUtils;

@RestController
public class CreateBusinessController {

	@Autowired
	private BusinessRepository repository;
	
	 Logger logger = LoggerFactory.getLogger(CreateBusinessController.class);

	@PostMapping(path = "/business")
	public ResponseEntity<BusinessDetails> persistBusiness(@Valid @RequestBody BusinessDetails businessDetails)
			throws Exception {
		if (isUserNameExists(businessDetails.getUserName(), null)) {
			return new ResponseEntity<>(repository.insertIntoDB(businessDetails), HttpStatus.CREATED);
		} else {
			logger.debug("UserName Already Exists");
			throw new Exception("UserName Already Exists");
		}
	}

	@PutMapping(path = "/business")
	public ResponseEntity<BusinessDetails> updateBusiness(@RequestBody BusinessDetails businessDetails)
			throws Exception {
		if (!StringUtils.isEmpty(businessDetails.getBusinessId())) {
			if (!StringUtils.isEmpty(businessDetails.getUserName())) {
				if (!isUserNameExists(businessDetails.getUserName(), businessDetails.getBusinessId())) {
					logger.debug("UserName Already Exists");
					throw new Exception("UserName Already Exists");					
				}
			}
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
			logger.error("Exception while copying properties", e);
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

	private boolean isUserNameExists(String userName, String updateId) {
		BusinessDetails customer = new BusinessDetails();
		customer.setUserName(userName);
		List<BusinessDetails> details = repository.getObject(customer);
		if (CollectionUtils.isEmpty(details)) {
			return true;
		} else {
			if (StringUtils.isEmpty(updateId)) {
				return false;
			} else {
				if (details.size() == 1) {
					return details.get(0).getBusinessId().equals(updateId);
				} else {
					return false;
				}
			}
		}

	}

	private BusinessDetails map(LoginDetails loginDetails) {
		BusinessDetails businessObj = new BusinessDetails();
		businessObj.setUserName(loginDetails.getUserName());
		businessObj.setPassword(loginDetails.getPassword());
		return businessObj;

	}

	@PostMapping(path = "/business/login")
	public ResponseEntity<List<BusinessDetails>> getLoginDetails(@RequestBody LoginDetails loginDetails)
			throws Exception {
		if (!EventUtil.validateLoginDetails(loginDetails)) {
			throw new Exception("Username / Password  is missing");
		} else {
			List<BusinessDetails> details = repository.getObject(map(loginDetails));
			if (CollectionUtils.isEmpty(details)) {
				throw new Exception("Invalid UserName / Password ");
			} else if (details.size() > 1) {
				throw new Exception("Multiple users exists ");
			} else {
				return new ResponseEntity<>(details, HttpStatus.OK);
			}

		}

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
