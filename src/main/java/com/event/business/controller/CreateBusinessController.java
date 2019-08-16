package com.event.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.BusinessDetails;
import com.event.business.util.BusinessRepository;

@RestController
public class CreateBusinessController {
	
	private final static String apiStatus = "{\n" +
	           "   \"Api Status\":\"API IS UP AND RUNNING\"\n" +
	           "}";
	
	@Autowired
	private BusinessRepository repository;
	
	@RequestMapping(path = "/business/health", method = RequestMethod.GET)
	public String healthCheck() {
		return apiStatus;
	}
	
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
	
	/*@RequestMapping(path = "/business/{user_name}", method = RequestMethod.GET)
	public RetreiveBusinessDetails retrieveBusinessDetailsByUsername(@PathParam(value="user_name") String user_name) {		
		return repository.getBusinessDetails(user_name);
	}*/
	
	
}
