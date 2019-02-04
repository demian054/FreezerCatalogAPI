package com.catalog.freezer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.freezer.model.Person;
import com.catalog.freezer.model.request.SignUpRequest;
import com.catalog.freezer.service.PersonService;


@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@PostMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity<Person> register(@Valid @RequestBody SignUpRequest sigUpPerson) {
		Person person= personService.save(sigUpPerson);
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Person>> getAllPersons() {
		return ResponseEntity.ok(personService.getAll());
	}

	@GetMapping(path = "/{person-id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Person> getPersonById(@PathVariable(name = "person-id", required = true ) final Long personId) {
		Person person = personService.findById(personId);
		if (person != null) {
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.notFound().build();
	}

}
