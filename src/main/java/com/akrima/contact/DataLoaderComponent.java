package com.akrima.contact;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.akrima.contact.documents.Employe;
import com.akrima.contact.repositories.EmployeReactiveRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
public class DataLoaderComponent {

	private final EmployeReactiveRepository employeReactiveRepository;

	public Flux<Employe> createEmployee() {
		
		Faker faker= new Faker();
		Date date1 = Date.from(LocalDate.of(2000, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date2 = Date.from(LocalDate.of(2019, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		List<Employe> employees=new ArrayList<>();
		
		for(int i=0; i<50;i++) {
			Employe employe = Employe.builder().nom(faker.name().lastName()).prenom(faker.name().firstName())
					.phoneNumber(faker.phoneNumber().cellPhone())
					.dateNaissance(faker.date().birthday())
					.dateEmbauche(faker.date().between(date1, date2))
					.office(faker.country().capital())
					.salary(Float.valueOf(faker.commerce().price(1000, 3000).replace(",", ".")))
					.position(faker.job().position())
					.build();
			employees.add(employe);
		}
		return employeReactiveRepository.saveAll(employees);
	}
}
