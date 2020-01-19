package com.akrima.contact.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.rowset.serial.SerialException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.akrima.contact.documents.Employe;
import com.akrima.contact.projections.EmployeProjection;
import com.akrima.contact.repositories.EmployeReactiveRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeService {

	private final EmployeReactiveRepository employeReactiveRepository;

	private final ModelMapper modelMapper;

	public Flux<EmployeProjection> findAll() {
		List<EmployeProjection> list = new ArrayList<>();
		Flux<Employe> all = Flux.fromStream(employeReactiveRepository.findAll().stream());
		all.subscribe(empl -> {
			list.add(modelMapper.map(empl, EmployeProjection.class));
		});
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Flux<EmployeProjection> flux = Flux.fromStream(list.stream());
		return flux;
	}

	public Mono<EmployeProjection> findById(String id) {
		return Mono.just(employeReactiveRepository.findById(id)).flatMap(empl -> {
			return Mono.just(modelMapper.map(empl, EmployeProjection.class));
		});
	}

	public Mono<EmployeProjection> save(Employe employe, MultipartFile multipartFile) {
		Employe newEmploye = modelMapper.map(employe, Employe.class);
		if (Objects.nonNull(multipartFile)) {
			try {
				try {
					newEmploye.setPhoto(new javax.sql.rowset.serial.SerialBlob(multipartFile.getBytes()));
				} catch (SerialException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Mono.just(employeReactiveRepository.save(newEmploye)).flatMap(empl -> {
			EmployeProjection employeProjection;
			try {
				employeProjection = EmployeProjection.builder().id(empl.getId()).nom(empl.getNom())
						.prenom(empl.getPrenom()).dateNaissance(empl.getDateNaissance())
						.dateEmbauche(empl.getDateEmbauche()).phoneNumber(empl.getPhoneNumber())
//					.conges(congesReactiveRepository.findById(empl.getIdConges()).block())
//					.salaire(salaireReactiveRepository.findById(empl.getIdSalaire()).block())
//					.fonction(fonctionReactiveRepository.findById(empl.getIdFonction()).block())
//					.service(serviceReactiveRepository.findById(empl.getIdService()).block())
						.photo(new javax.sql.rowset.serial.SerialBlob(empl.getPhoto())).build();
				return Mono.just(employeProjection);
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Mono.empty();
		});
	}

	public Flux<EmployeProjection> delete(String id) {
		 employeReactiveRepository.deleteById(id);
		return findAll();
	}
}
