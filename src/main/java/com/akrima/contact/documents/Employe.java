package com.akrima.contact.documents;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String nom;
	
	private String prenom;
	
	private Date dateNaissance;
	
	private Date dateEmbauche;
	
	private String phoneNumber;
	
	private Blob photo;
	
	private String office;
	
	private float salary;
	
	private String position;
}
