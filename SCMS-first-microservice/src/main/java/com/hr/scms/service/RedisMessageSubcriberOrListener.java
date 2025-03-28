package com.hr.scms.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubcriberOrListener implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel= new String(pattern);		
		System.out.println("LimitService Channel=> "+channel +"=======  "+message.getChannel());
		System.out.println("Message Received at Limit Microservice=> "+ message + "/n"+ message.getBody());
	}

}
