package com.event.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.LoginDetails;
import com.event.business.util.LoginRepository;

@RestController
public class LoginController {
	
	@Autowired
	private LoginRepository repository;
	
	@PostMapping(path = "/loginDetails")
	public ResponseEntity<LoginDetails> persistBusiness(@RequestBody LoginDetails loginDetails) {
		return new ResponseEntity<>(repository.insertIntoDB(loginDetails), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/loginDetails")
	public ResponseEntity<LoginDetails> updateBusiness(@RequestBody LoginDetails loginDetails) {
		if(!StringUtils.isEmpty(loginDetails.getLoginDetailsId())) {
			return new ResponseEntity<>(repository.updateIntoDB(loginDetails, loginDetails.getLoginDetailsId()), HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
			
}
