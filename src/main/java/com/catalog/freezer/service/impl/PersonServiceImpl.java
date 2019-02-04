package com.catalog.freezer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalog.freezer.exception.PersonException;
import com.catalog.freezer.model.Person;
import com.catalog.freezer.model.RoleName;
import com.catalog.freezer.model.request.SignUpRequest;
import com.catalog.freezer.repository.PersonRepository;
import com.catalog.freezer.security.JwtPersonPrincipal;
import com.catalog.freezer.service.PersonService;
import com.catalog.freezer.service.RoleService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private static ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<Person> getAll() {
		List<Person> personList = new ArrayList<>();
		personRepository.findAll().forEach(personList::add);
		return personList;
	}

	@Override
	public Person save(SignUpRequest model) {
		Person personToSingUp = modelMapper.map(model, Person.class);
		if (emailSave(personToSingUp.getEmail())) {
			throw new PersonException("Email is in used please use other email", "creating new User", "email in use");
		}
		personToSingUp.setRegistrationNumber(passwordEncoder.encode(personToSingUp.getRegistrationNumber()));
		personToSingUp.getRoles().add(roleService.findRoleByName(RoleName.ROLE_PERSON));
		System.out.println(personToSingUp);
		return personRepository.save(personToSingUp);
	}

	@Override
	public Boolean emailSave(String email) {
		return personRepository.findPersonByEmail(email).isPresent();
	}

	@Override
	public Person findByEmail(String email) {
		return personRepository.findPersonByEmail(email)
				.orElseThrow(() -> new PersonException("person by Email", email, "Person not found"));
	}

	@Transactional
	public UserDetails loadUserByEmail(String email) {
		Person user = personRepository.findPersonByEmail(email)
				.orElseThrow(() -> new PersonException("User Email", email, "User not found"));
		return JwtPersonPrincipal.create(user);
	}

	@Override
	public Person findById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonException("person by id", id.toString(), "Person not found"));
	}

}
