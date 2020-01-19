package com.akrima.contact;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.akrima.contact.documents.Employe;
import com.akrima.contact.repositories.EmployeReactiveRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
public class DataLoaderComponent {

	private final EmployeReactiveRepository employeReactiveRepository;
	
	private Resource loadPhoto(int index) {
	    return new ClassPathResource("images/"+index+".jpeg");
	}

	public Flux<Employe> createEmployee() {
		
		Faker faker= new Faker();
		Date date1 = Date.from(LocalDate.of(2000, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date2 = Date.from(LocalDate.of(2019, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		List<Employe> employees=new ArrayList<>();
		
		for(int i=1; i<=50;i++) {
			Employe employe;
			try {
				
				try {
					employe = Employe.builder().nom(faker.name().lastName()).prenom(faker.name().firstName())
							.phoneNumber(faker.phoneNumber().cellPhone())
							.dateNaissance(faker.date().birthday())
							.dateEmbauche(faker.date().between(date1, date2))
							.office(faker.country().capital())
							.salary(Float.valueOf(faker.commerce().price(1000, 3000).replace(",", ".")))
							.position(faker.job().position())
							.photo(new javax.sql.rowset.serial.SerialBlob(Files.readAllBytes(loadPhoto(i).getFile().toPath())))
							.build();
					employees.add(employe);
				} catch (SerialException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Flux.fromIterable(employeReactiveRepository.saveAll(employees));
	}
}
