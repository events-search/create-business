package com.event.business.controller;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.Signup;
import com.event.business.util.SignUpRepository;

@RestController
public class SignupController {
	
	private final static String apiStatus = "{\n" +
	           "   \"Api Status\":\"API IS UP AND RUNNING\"\n" +
	           "}";
	
	@Autowired
	private SignUpRepository repository;
	
	@RequestMapping(path = "/signup/health", method = RequestMethod.GET)
	public String healthCheck() {
		return apiStatus;
	}
	
	@PostMapping(path = "/signup")
	public void persistBusiness(@RequestBody Signup signup) {
		repository.insertIntoDB(signup);
	}
	
	@PutMapping(path = "/signup")
	public void updateBusiness(@RequestBody Signup signup) {
		if(!StringUtils.isEmpty(signup.getSignupId())) {
		repository.updateIntoDB(signup, signup.getSignupId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
	@GetMapping(path = "/signup/{user_name}")
	public Collection<Signup> getSignup(@PathParam(value="user_name") String emailId ) {
		return repository.search(emailId);
	}
	/*@RequestMapping(path = "/business/{user_name}", method = RequestMethod.GET)
	public RetreiveBusinessDetails retrieveBusinessDetailsByUsername(@PathParam(value="user_name") String user_name) {		
		return repository.getBusinessDetails(user_name);
	}
	
	@RequestMapping(path = "/business/retrieve", method = RequestMethod.GET)
	public RetreiveBusinessDetails retrieveBusinessDetails() {
		
		return repository.getBusinessDetails("09ca9cea-9f69-4282-8d01-c897be746f9e");
		/*
		RetreiveBusinessDetails retreiveBusinessDetails = new RetreiveBusinessDetails();
		
		Address address = new Address();
		address.setAddressLine1("2203 clifton park cir");
		address.setAddressType(AddressType.OFFICE);
		address.setCity("Wilmington");
		address.setState("DE");
		address.setZipCode("19802");
		
		Phone phone = new Phone();
		phone.setPhoneNumber("3617205898");
		phone.setPhoneType(PhoneType.OFFICE);
		
		retreiveBusinessDetails.setAddress(address);
		retreiveBusinessDetails.setBusinessName("Event Organizer");
		retreiveBusinessDetails.setBusinessType("Music");
		retreiveBusinessDetails.setDescription("We arrange dance floors with DJ");
		retreiveBusinessDetails.setPhone(phone);
		return retreiveBusinessDetails;*/
	/*}
*/
}
