package com.akrima.contact.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
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
		Flux<Employe> all = employeReactiveRepository.findAll();
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
		return employeReactiveRepository.findById(id).flatMap(empl -> {
			return Mono.just(modelMapper.map(empl, EmployeProjection.class));
		});
	}

	public Mono<EmployeProjection> save(Employe employe, MultipartFile multipartFile) {
		Employe newEmploye = modelMapper.map(employe, Employe.class);
		if (Objects.nonNull(multipartFile)) {
//			try {
//				newEmploye.setPhoto(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return employeReactiveRepository.save(newEmploye).flatMap(empl -> {
			EmployeProjection employeProjection = EmployeProjection.builder().id(empl.getId()).nom(empl.getNom())
					.prenom(empl.getPrenom()).dateNaissance(empl.getDateNaissance())
					.dateEmbauche(empl.getDateEmbauche()).phoneNumber(empl.getPhoneNumber())
//					.conges(congesReactiveRepository.findById(empl.getIdConges()).block())
//					.salaire(salaireReactiveRepository.findById(empl.getIdSalaire()).block())
//					.fonction(fonctionReactiveRepository.findById(empl.getIdFonction()).block())
//					.service(serviceReactiveRepository.findById(empl.getIdService()).block())
					.photo(empl.getPhoto()).build();
			return Mono.just(employeProjection);
		});
	}

	public Flux<EmployeProjection> delete(String id) {
		 employeReactiveRepository.deleteById(id);
		return findAll();
	}
}
