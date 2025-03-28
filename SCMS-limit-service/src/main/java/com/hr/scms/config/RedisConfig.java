package com.hr.scms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.hr.scms.config.service.RedisMessageSubcriberOrListener;

@Configuration
public class RedisConfig {
	
//	@Bean
//	RedisConnectionFactory redisConnectionFactory() {
//	    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//	    config.setHostName("localhost");
//	    config.setPort(6379);
//	    config.setPassword("123456"); // If authentication is required
//	    return new LettuceConnectionFactory(config);
//	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setConnectionFactory(connectionFactory);
		return template;
	}

	@Bean
	MessageListenerAdapter messageListenerAdapter(RedisMessageSubcriberOrListener subscriber) {
		return new MessageListenerAdapter(subscriber);
	}

	@Bean
	RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new ChannelTopic("channel1"));
		container.addMessageListener(listenerAdapter, new ChannelTopic("channel2"));
		container.addMessageListener(listenerAdapter, new ChannelTopic("channel3"));
		return container;
	}

}
