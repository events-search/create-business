package com.event.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.BusinessDetails;
import com.event.business.util.BusinessRepository;

@RestController
public class CreateBusinessController {
	
	@Autowired
	private BusinessRepository repository;
	
	@PostMapping(path = "/business")
	public void persistBusiness(@RequestBody BusinessDetails businessDetails) {
		repository.insertIntoDB(businessDetails);
	}
	
	@PutMapping(path = "/business")
	public void updateBusiness(@RequestBody BusinessDetails businessDetails) {
		if(!StringUtils.isEmpty(businessDetails.getBusinessId())) {
		repository.updateIntoDB(businessDetails, businessDetails.getBusinessId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
	@GetMapping(path = "/business/{business_id}")
	public BusinessDetails getBusinessById(@PathVariable(value="business_id", required=true) String businessId  ) {
		  return repository.getById(businessId);
	 }
	
	@GetMapping(path = "/business")
	public List<BusinessDetails> getBusiness() {
		  return repository.getAll();
	 }
	
}
