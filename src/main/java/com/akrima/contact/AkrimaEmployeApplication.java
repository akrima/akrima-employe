package com.akrima.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.akrima.contact.repositories.EmployeReactiveRepository;

@SpringBootApplication
public class AkrimaEmployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkrimaEmployeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(DataLoaderComponent dataLoaderComponent,
			EmployeReactiveRepository employeReactiveRepository) {
		return args -> {
			dataLoaderComponent.createEmployee().subscribe(empl -> {
				//System.out.println("---------------------> "+empl.toString());
			});
		};
	}

}
