package com.may.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*configuration class to configure the connection to Redis using Jedis.*/
@Configuration
@PropertySource({ "classpath:redis.properties" })
public class RedisConfig {

	@Autowired
	private Environment env;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(env.getProperty("redis.hostname"),
				Integer.valueOf(env.getProperty("redis.port")));
		// config.setPassword("your_redis_password"); // Uncomment if Redis is
		// password-protected
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.afterPropertiesSet();
		return template;
	}

}
