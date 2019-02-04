package com.catalog.freezer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.catalog.freezer.model.Person;
import com.catalog.freezer.repository.PersonRepository;
import com.catalog.freezer.security.JwtAuthenticationResponse;

import io.jsonwebtoken.Jwts;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest extends ConfigTest {

	private static final String URL = "/api/auth/signin";

	@Autowired
	private Environment environment;

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testAuthPersonSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(getContent(EMAIL, REGISTRATION_NUMBER)))
				.andExpect(status().isOk()).andReturn();
		
		JwtAuthenticationResponse mockedResponse = mapper.readValue(
				result.getResponse().getContentAsString(),
				JwtAuthenticationResponse.class
			);
		
		String personEmail = Jwts.parser()
				.setSigningKey(environment.getProperty("app.jwtSecret"))
				.parseClaimsJws(mockedResponse.getAccessToken()).getBody().getSubject();
		
		Person person = personRepository.findPersonByEmail(EMAIL).orElseThrow(Exception::new);
		Assert.assertEquals(person.getEmail(), personEmail);
	}

	@Test
	public void testAuthPersonBadCredentials() throws Exception {
		mockMvc.perform(
				post(URL).contentType(MediaType.APPLICATION_JSON).content(getContent("juan@gmail.com", "randomPassword")))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAuthPersonInvalidCredentials() throws Exception {
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(getContent("noemail", "otherRandomPassword")))
				.andExpect(status().isBadRequest());
	}

	private String getContent(String login, String password) {
		return mapper.createObjectNode().put("email", login).put("registrationNumber", password).toString();
	}
}
