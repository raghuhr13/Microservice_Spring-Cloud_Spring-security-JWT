package com.hr.scms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.scms.config.service.RedisMessagePublisher;

@RestController
public class RedisMessageController {
	
	@Autowired
	private RedisMessagePublisher redisMessagePublishing;
	
	
	@PostMapping("/postA")
	public void sendMess(@RequestParam String channel, @RequestParam String message) {
		redisMessagePublishing.publish(channel, message);
	}

}
