package com.catalog.freezer;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.catalog.freezer.security.JwtAuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigTest {
	
	public static final String ADMIN_EMAIL = "adminUser@gmail.com";
	public static final String ADMIN_REGISTRATION_NUMBER = "123456";
	public static final String EMAIL = "testUser@gmail.com";
	public static final String REGISTRATION_NUMBER = "123456";
	

	@Autowired
	private WebApplicationContext context;
	


	protected MockMvc mockMvc;
	protected ObjectMapper mapper;

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		this.mapper = new ObjectMapper();
	}	
	
	protected String getToken(String login, String password) throws Exception {
		String bodyResponseAuthentication = mockMvc
				.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON).content(
						mapper.createObjectNode().put("email", login).put("registrationNumber", password).toString()))
				.andReturn().getResponse().getContentAsString();
		JwtAuthenticationResponse response = mapper.readValue(bodyResponseAuthentication,
				JwtAuthenticationResponse.class);

		return "Bearer " + response.getAccessToken();
	}

}
