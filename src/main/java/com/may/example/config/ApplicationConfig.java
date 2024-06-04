package com.may.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PersistenceJpaConfig.class })
public class ApplicationConfig {

//	@Bean
//	public InternPersonService internPersonService() {
//		return new InternPersonServiceImpl();
//	}
}
