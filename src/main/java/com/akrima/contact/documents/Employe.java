package com.akrima.contact.documents;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employe {

	@Id
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
