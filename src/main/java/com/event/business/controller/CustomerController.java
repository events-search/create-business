package com.event.business.controller;

import java.util.Collection;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping(path = "/customers")
	public void persistBusiness(@RequestBody Customer Customer) {
		repository.insertIntoDB(Customer);
	}
	
	@PutMapping(path = "/customers")
	public void updateBusiness(@RequestBody Customer Customer) {
		if(!StringUtils.isEmpty(Customer.getCustomerId())) {
		repository.updateIntoDB(Customer, Customer.getCustomerId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
	@GetMapping(path = "/customers/{user_name}")
	public Collection<Customer> getCustomer(@PathParam(value="user_name") String emailId ) {
		return repository.search(emailId);
	}
	
	@GetMapping(path = "/customers/{cust_id}")
	public Customer getCustomerById(@PathVariable(value="cust_id", required=true) String custId  ) {
		  return repository.getById(custId);
	 }
	
	@GetMapping(path = "/customers")
	public List<Customer> getCustomer() {
		  return repository.getAll();
	 }
	
}
