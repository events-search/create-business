package com.event.business.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.Customer;
import com.event.business.model.LoginDetails;
import com.event.business.util.CustomerRepository;
import com.event.business.util.EventUtil;
import com.event.business.util.MyBeanUtils;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping(path = "/customer")
	public ResponseEntity<Customer> persistCustomer(@Valid @RequestBody Customer customer) throws Exception {
		if (isUserNameExists(customer.getUserName(), null)) {
			return new ResponseEntity<>(repository.insertIntoDB(customer), HttpStatus.CREATED);
		} else {
			logger.debug("UserName Already Exists");
			throw new Exception("UserName Already Exists");
		}
	}

	@PutMapping(path = "/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
		if (!StringUtils.isEmpty(customer.getCustomerId())) {
			if (!StringUtils.isEmpty(customer.getUserName())) {
				if (!isUserNameExists(customer.getUserName(), customer.getCustomerId())) {
					throw new Exception("UserName Already Exists");
				}
			}

			return new ResponseEntity<>(repository.updateIntoDB(getUpdatedCustomer(customer), customer.getCustomerId()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	private Customer getUpdatedCustomer(Customer customer) {
		Customer customerObj = repository.getById(customer.getCustomerId());
		try {
			MyBeanUtils.copyPropertiesNotNull(customerObj, customer);
		} catch (InvocationTargetException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerObj;
	}

	@PostMapping(path = "/customer/search")
	public ResponseEntity<List<Customer>> getSearchCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(repository.getObject(customer), HttpStatus.OK);
	}

	@GetMapping(path = "/customer/{cust_id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "cust_id", required = true) String custId) {
		return new ResponseEntity<>(repository.getById(custId), HttpStatus.OK);
	}

	@GetMapping(path = "/customer")
	public ResponseEntity<List<Customer>> getCustomer() {
		return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/customer/search/{user_name}")
	public ResponseEntity<Customer> getSearchCustomerByUserName(
			@PathVariable(value = "user_name", required = true) String userName) throws Exception {
		Customer customer = new Customer();
		customer.setUserName(userName);
		List<Customer> customerList = repository.getObject(customer);
		if (CollectionUtils.isEmpty(customerList)) {
			throw new Exception("no customer exists with username: " + userName);
		} else if (customerList.size() > 1) {
			throw new Exception("multiple customer exists with username: " + userName);
		}
		return new ResponseEntity<>(customerList.get(0), HttpStatus.OK);
	}

	private Customer map(LoginDetails loginDetails) {
		Customer customer = new Customer();
		customer.setUserName(loginDetails.getUserName());
		customer.setPassword(loginDetails.getPassword());
		return customer;

	}

	private boolean isUserNameExists(String userName, String updateId) {
		Customer customer = new Customer();
		customer.setUserName(userName);
		List<Customer> details = repository.getObject(customer);
		if (CollectionUtils.isEmpty(details)) {
			return true;
		} else {
			if (StringUtils.isEmpty(updateId)) {
				return false;
			} else {
				if (details.size() == 1) {
					return details.get(0).getCustomerId().equals(updateId);
				} else {
					return false;
				}
			}
		}

	}

	@PostMapping(path = "/customer/login")
	public ResponseEntity<List<Customer>> getLoginDetails(@RequestBody LoginDetails loginDetails) throws Exception {
		if (!EventUtil.validateLoginDetails(loginDetails)) {
			throw new Exception("Username / Password  is missing");
		} else {
			List<Customer> details = repository.getObject(map(loginDetails));
			if (CollectionUtils.isEmpty(details)) {
				throw new Exception("Invalid UserName / Password ");
			} else if (details.size() > 1) {
				throw new Exception("Multiple users exists ");
			} else {
				return new ResponseEntity<>(details, HttpStatus.OK);
			}

		}

	}

}
