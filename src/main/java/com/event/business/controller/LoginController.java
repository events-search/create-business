package com.event.business.controller;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
	public void persistBusiness(@RequestBody LoginDetails loginDetails) {
		repository.insertIntoDB(loginDetails);
	}
	
	@PutMapping(path = "/loginDetails")
	public void updateBusiness(@RequestBody LoginDetails loginDetails) {
		if(!StringUtils.isEmpty(loginDetails.getLoginDetailsId())) {
		repository.updateIntoDB(loginDetails, loginDetails.getLoginDetailsId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
	@GetMapping(path="/loginDetails/{user_name}")
	public  Collection<LoginDetails> getServiceTypeByCode(@PathParam(value="user_name") String userName){
       return repository.getItem(userName);
       
	}
		
}
