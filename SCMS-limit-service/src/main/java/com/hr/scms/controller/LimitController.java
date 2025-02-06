package com.hr.scms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.scms.config.Configuration;
import com.hr.scms.dto.Limits;

@RestController
@RequestMapping("/SCMS-limit-microservice")
public class LimitController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/getLimits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMin(), configuration.getMax());
	}

}
