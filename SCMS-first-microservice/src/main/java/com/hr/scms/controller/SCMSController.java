package com.hr.scms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.scms.service.RedisMessagePublisher;

@RestController
@RequestMapping("/SCMS-first-microservice")
public class SCMSController {
	
	@Autowired
	private RedisMessagePublisher redisMessagePublishing;
	
	@GetMapping("/test-1")
	public String data() {
		return "Data From First Micro Service";
	}
	
	@PostMapping("/postA")
	public void sendMess(@RequestParam String channel, @RequestParam String message) {
		redisMessagePublishing.publish(channel, message);
	}
	
}
