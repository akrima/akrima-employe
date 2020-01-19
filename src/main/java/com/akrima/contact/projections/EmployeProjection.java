package com.akrima.contact.projections;

import java.util.Date;

import org.bson.types.Binary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeProjection {

	private String id;

	private String nom;

	private String prenom;

	private Date dateNaissance;

	private Date dateEmbauche;

	private String phoneNumber;
	
	private Binary photo;
	
	private String office;
	
	private float salary;
	
	private String position;
}