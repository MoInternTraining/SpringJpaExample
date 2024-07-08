package com.may.example.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.may.example.config.ApplicationConfig;
import com.may.example.model.InternPerson;
import com.may.example.service.InternPersonService;
import com.may.example.service.redis.RedisService;

public class InternPersonApplication {
	private static final Logger LOG = LoggerFactory.getLogger(InternPersonApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		// everything wires up across configuration classes...
		InternPerson person = new InternPerson(2L, "May Thin", "Khaing", "maythin@gmail.com", "09778661178", "Taungoo");
		InternPersonService internPersonService = ctx.getBean(InternPersonService.class);
//		internPersonService.createInternPerson(person);
//
//		List<InternPerson> internPersons = internPersonService.findInternPersons(0, 2, "maythin@gmail.com", null);
//		internPersons.forEach(
//				intern -> LOG.info("Email : " + intern.getEmail() + "\n" + "Phone Number :" + intern.getPhoneNo()));

		RedisService redisService = ctx.getBean(RedisService.class);

		// Set and Get values from Redis
		redisService.setValue("myKey", "myValue");
		Object retrievedValue = redisService.getValue("myKey");
		LOG.info("Retrieved value from Redis: " + retrievedValue);

		redisService.cacheInternPersonInRedis();
		Object retrievedPerson = redisService.getInternPersonFromRedis(2);
		LOG.info("Retrieved person from Redis: " + retrievedPerson);

	}

}
