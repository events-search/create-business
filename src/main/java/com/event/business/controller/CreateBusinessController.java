package com.event.business.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.RetreiveBusinessDetails;
import com.event.business.util.DynamoDBRepository;

@RestController
public class CreateBusinessController {
	
	private final static String apiStatus = "{\n" +
	           "   \"Api Status\":\"API IS UP AND RUNNING\"\n" +
	           "}";
	
	@Autowired
	private DynamoDBRepository repository;
	
	@RequestMapping(path = "/business/health", method = RequestMethod.GET)
	public String healthCheck() {
		return apiStatus;
	}
	
	@PostMapping(path = "/business")
	public void persistBusiness(@RequestBody RetreiveBusinessDetails businessDetails) {
		repository.insertIntoDB(businessDetails);
	}
	
	@RequestMapping(path = "/business/{user_name}", method = RequestMethod.GET)
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
	}

}
