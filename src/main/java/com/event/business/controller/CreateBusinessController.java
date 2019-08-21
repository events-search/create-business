package com.event.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<BusinessDetails> persistBusiness(@RequestBody BusinessDetails businessDetails) {
		return new ResponseEntity<>(repository.insertIntoDB(businessDetails), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/business")
	public ResponseEntity<BusinessDetails> updateBusiness(@RequestBody BusinessDetails businessDetails) {
		if(!StringUtils.isEmpty(businessDetails.getBusinessId())) {
			return new ResponseEntity<>(repository.updateIntoDB(businessDetails, businessDetails.getBusinessId()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/business/{business_id}")
	public ResponseEntity<BusinessDetails> getBusinessById(@PathVariable(value="business_id", required=true) String businessId  ) {
		return new ResponseEntity<>(repository.getById(businessId), HttpStatus.OK);
	 }
	
	@GetMapping(path = "/business")
	public ResponseEntity<List<BusinessDetails>> getBusiness() {
		  return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
	 }
	
}
