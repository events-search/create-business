package com.event.business.controller;

import java.util.Collection;
import java.util.List;

import javax.websocket.server.PathParam;

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

import com.event.business.model.Customer;
import com.event.business.util.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	@PostMapping(path = "/customer")
	public ResponseEntity<Customer> persistBusiness(@RequestBody Customer customer) {
		return new ResponseEntity<>(repository.insertIntoDB(customer), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/customer")
	public ResponseEntity<Customer> updateBusiness(@RequestBody Customer Customer) {
		if(!StringUtils.isEmpty(Customer.getCustomerId())) {
			return new ResponseEntity<>(repository.updateIntoDB(Customer, Customer.getCustomerId()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/customer/{user_name}")
	public Collection<Customer> getCustomer(@PathParam(value="user_name") String emailId ) {
		return repository.search(emailId);
	}
	
	@GetMapping(path = "/customer/{cust_id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value="cust_id", required=true) String custId  ) {
		  return new ResponseEntity<>(repository.getById(custId), HttpStatus.OK);
	 }
	
	@GetMapping(path = "/customer")
	public ResponseEntity<List<Customer>> getCustomer() {
		 return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
	 }
	
}
