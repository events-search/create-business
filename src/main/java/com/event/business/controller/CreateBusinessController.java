package com.event.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.enums.AddressType;
import com.event.business.enums.PhoneType;
import com.event.business.model.Address;
import com.event.business.model.Phone;
import com.event.business.model.RetreiveBusinessDetails;

@RestController
public class CreateBusinessController {
	
	private final static String apiStatus = "{\n" +
	           "   \"Api Status\":\"API IS UP AND RUNNING\"\n" +
	           "}";
	
	@RequestMapping(path = "/business/health", method = RequestMethod.GET)
	public String healthCheck() {
		return apiStatus;
	}
	
	@RequestMapping(path = "/business/retrieve", method = RequestMethod.GET)
	public RetreiveBusinessDetails retrieveBusinessDetails() {
		
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
		return retreiveBusinessDetails;
	}

}
