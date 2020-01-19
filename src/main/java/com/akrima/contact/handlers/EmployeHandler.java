package com.akrima.contact.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.akrima.contact.documents.Employe;
import com.akrima.contact.services.EmployeService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeHandler {

	private final EmployeService employeService;

	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employeService.findAll(),
				Employe.class);
	}

	public Mono<ServerResponse> findOn(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(employeService.findById(request.pathVariable("id")), Employe.class);
	}
	
	public Mono<ServerResponse> upload(ServerRequest request) {
		return request.body(BodyExtractors.toMono(Employe.class)).flatMap(empl->{
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
					.body(employeService.save(empl,null), Employe.class); 
		});
	}
	
	public Mono<ServerResponse> post(ServerRequest request) {
		return request.body(BodyExtractors.toMono(Employe.class)).flatMap(empl->{
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(employeService.save(empl,null), Employe.class); 
		});
	}
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employeService.delete(request.pathVariable("id")),
				Employe.class);
	}
}
