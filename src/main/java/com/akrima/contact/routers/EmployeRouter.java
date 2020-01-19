package com.akrima.contact.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.akrima.contact.handlers.EmployeHandler;

@Configuration
public class EmployeRouter {

	@Bean
	public RouterFunction<ServerResponse>  route(EmployeHandler employeHandler){
		return RouterFunctions.route()
			     .path("/employe", builder ->
			       builder.GET("/all",employeHandler::findAll)
			              .GET("/{id}",employeHandler::findOn)
			              .DELETE("/{id}",employeHandler::delete))
			     .build();
	}
}
