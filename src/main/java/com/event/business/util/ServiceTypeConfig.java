package com.event.business.util;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "services")
public class ServiceTypeConfig {
	
	private HashMap<String, String> offered;

	public HashMap<String, String> getOffered() {
		return offered;
	}

	public void setOffered(HashMap<String, String> offered) {
		this.offered = offered;
	}
	
	

}
