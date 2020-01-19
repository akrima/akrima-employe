package com.akrima.contact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry
		.addMapping("/**")
		.allowedMethods("*")
		.allowedHeaders("*")
		.allowedOrigins("*")
		.maxAge(3600);
		;
	}

}