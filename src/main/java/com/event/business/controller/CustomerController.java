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

import com.event.business.model.Customer;
import com.event.business.util.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	@PostMapping(path = "/Customer")
	public void persistBusiness(@RequestBody Customer Customer) {
		repository.insertIntoDB(Customer);
	}
	
	@PutMapping(path = "/Customer")
	public void updateBusiness(@RequestBody Customer Customer) {
		if(!StringUtils.isEmpty(Customer.getCustomerId())) {
		repository.updateIntoDB(Customer, Customer.getCustomerId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
	@GetMapping(path = "/Customer/{user_name}")
	public Collection<Customer> getCustomer(@PathParam(value="user_name") String emailId ) {
		return repository.search(emailId);
	}
	
}
