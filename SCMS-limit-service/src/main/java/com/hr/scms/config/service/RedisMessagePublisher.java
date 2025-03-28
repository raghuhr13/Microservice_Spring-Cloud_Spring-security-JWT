package com.hr.scms.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {
	
	//To handle various data types, including JSON, serialized objects, etc
    private final RedisTemplate<String, Object> redisTemplate;
    
    @Autowired //Specifically designed for working with String keys and String values.
    private StringRedisTemplate stringRedisTemplate;
    
    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
        stringRedisTemplate.convertAndSend(channel, message);
    }


}
