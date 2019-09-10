package com.event.business.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.util.ConfigurationService;

@RestController
public class ConfigurationController {
	
	@Autowired
	private ConfigurationService configurationService;

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	enum ConfigTypes {
		business_types, service_types, address_types, mobile_types
	};

	@GetMapping(path = "/configuration/{config_key}")
	public ResponseEntity<Map<String, String>> getConfigurationOnKey(
			@PathVariable(value = "config_key", required = true) String configKey) {
		if (ConfigTypes.service_types.equals(ConfigTypes.valueOf(configKey))) {
			return new ResponseEntity<>(getConfigurationService().getServiceTypes(), HttpStatus.OK);
		} else if (ConfigTypes.mobile_types.equals(ConfigTypes.valueOf(configKey))) {
			return new ResponseEntity<>(getConfigurationService().getMobileTypes(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
