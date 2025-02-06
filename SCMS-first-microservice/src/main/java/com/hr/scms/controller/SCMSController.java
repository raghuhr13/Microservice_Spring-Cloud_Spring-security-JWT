package com.hr.scms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SCMS-first-microservice")
public class SCMSController {
	
	@GetMapping("/test-1")
	public String data() {
		return "Data From First Micro Service";
	}

}
