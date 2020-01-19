package com.akrima.contact.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.akrima.contact.documents.Employe;

public interface EmployeReactiveRepository extends ReactiveMongoRepository<Employe, String> {

	
}
