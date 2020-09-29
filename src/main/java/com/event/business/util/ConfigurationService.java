package com.event.business.util;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "configuration")
public class ConfigurationService {
	
	private HashMap<String, String> businessTypes;

	private HashMap<String, String> mobileTypes;
	
	private HashMap<String, String> serviceTypes;

	public HashMap<String, String> getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(HashMap<String, String> businessTypes) {
		this.businessTypes = businessTypes;
	}

	public HashMap<String, String> getMobileTypes() {
		return mobileTypes;
	}

	public void setMobileTypes(HashMap<String, String> mobileTypes) {
		this.mobileTypes = mobileTypes;
	}

	public HashMap<String, String> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(HashMap<String, String> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
	

}
