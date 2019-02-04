package com.catalog.freezer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.catalog.freezer.dto.ExceptionDTO;
import com.catalog.freezer.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest extends ConfigTest {


	@Test
	public void testPersonRegisterSuccess() throws Exception {
		String content = mapper.createObjectNode()
				.put("name", "demian bolivar")
				.put("email", "demian.bolivar@mail.com")
				.put("registrationNumber", REGISTRATION_NUMBER).toString();
		MvcResult result = mockMvc.perform(post("/api/person").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isCreated()).andReturn();
		Person person = mapper.readValue(result.getResponse().getContentAsByteArray(), Person.class);
		assertEquals("demian bolivar", person.getName());
	}

	@Test
	public void testGetAllPersonsSuccess() throws Exception {
		MvcResult result = mockMvc.perform(
				get("/api/person").header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER)))
				.andExpect(status().isOk()).andReturn();
		List<Person> persons = mapper.readValue(result.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
		assertTrue(persons.size() >= 2);
		Person admin = persons.stream().filter(person -> person.getEmail().equals(ADMIN_EMAIL)).findFirst()
				.orElse(null);
		assertNotNull(admin);
	}

	@Test
	public void testGetPersonByIdSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/person/2").header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER)))
				.andExpect(status().isOk()).andReturn();
		Person person = mapper.readValue(result.getResponse().getContentAsString(), Person.class);
		assertEquals(EMAIL, person.getEmail());
	}

	@Test
	public void testGetPersonByIdFail() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/person/999").header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		ExceptionDTO errorDetails = mapper.readValue(result.getResponse().getContentAsString(), ExceptionDTO.class);
		assertEquals("Person not found", errorDetails.getMessage());
	}

}
