package com.owra.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
	
	@Value("${spring.redis.host}")
	String host;
 
	@Value("${spring.redis.port}")
	int port;
	
	@Value("${spring.redis.database}")
	int database;
	
	@Value("${spring.redis.password}")
	private String redisPassword;
	
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(redisPassword);
		factory.setUsePool(true);
		factory.afterPropertiesSet();
		return factory;
	}
}
