package com.akrima.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.akrima.contact.repositories.EmployeReactiveRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AkrimaEmployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkrimaEmployeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(DataLoaderComponent dataLoaderComponent,
			EmployeReactiveRepository employeReactiveRepository) {
		return args -> {
			dataLoaderComponent.createEmployee().subscribe(empl -> {
				log.info("---------------------> "+empl.toString());
			});
		};
	}

}
