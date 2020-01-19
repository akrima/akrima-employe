package com.akrima.contact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akrima.contact.documents.Employe;

public interface EmployeReactiveRepository extends JpaRepository<Employe, String> {

	
}
