package com.event.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.ServiceType;
import com.event.business.util.ServiceTypeRepository;

@RestController
public class ServiceTypeController {
	
	@Autowired
	private ServiceTypeRepository repository;

	@PostMapping(path = "/serviceType")
	public void persistBusiness(@RequestBody ServiceType serviceType) {
		repository.insertIntoDB(serviceType);
	}
	
	@PutMapping(path = "/serviceType")
	public void updateBusiness(@RequestBody ServiceType serviceType) {
		if(!StringUtils.isEmpty(serviceType.getServiceTypeId())) {
		repository.updateIntoDB(serviceType, serviceType.getServiceTypeId());
		}else {
			//need to throw invalidParameterException
		}
	}
	
}
