package com.may.example.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.may.example.config.ApplicationConfig;
import com.may.example.model.InternPerson;
import com.may.example.service.InternPersonService;

public class InternPersonApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		// everything wires up across configuration classes...
		InternPerson person = new InternPerson(1,"May", "Thin", "maythin@gmail.com", "09778665778", "Yangon");
		InternPersonService internPersonService = ctx.getBean(InternPersonService.class);
		internPersonService.createInternPerson(person);

	}

}
