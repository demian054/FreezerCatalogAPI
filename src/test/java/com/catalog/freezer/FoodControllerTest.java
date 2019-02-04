package com.catalog.freezer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.catalog.freezer.model.Food;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class FoodControllerTest extends ConfigTest {

	private static final String URL = "/api/foods";

	private static final String testFoodId = "/1";
	private static final String testFoodName = "Pork";
	private static final Long testFoodQuantity = 7L;
	private static final String testFoodTypeName = "Meet";
	private static final String testAddText = "_New";

	private static final String testSearchFoodName = "bReAd";
	private static final String testSearchFoodTypeName = "fRuIt";
	
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	@Test
	public void testGetAllFoodsSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(get(URL).header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

		List<Food> foods = mapper.readValue(result.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Food.class));
		assertTrue(foods.size() >= 1);
	}

	@Test
	public void testAddFoodSuccess() throws Exception {

		String content = mapper.createObjectNode().put("name", testFoodName).put("quantity", testFoodQuantity)
				.put("foodType", testFoodTypeName).toString();

		MvcResult result = mockMvc
				.perform(post(URL).header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isCreated()).andReturn();

		Food food = mapper.readValue(result.getResponse().getContentAsString(), Food.class);

		System.out.println(food);

		assertEquals(testFoodName, food.getName());
		assertEquals(testFoodQuantity, food.getQuantity());
		assertEquals(testFoodTypeName, food.getFoodType().getName());
	}

	@Test
	public void testAddFoodAccess() throws Exception {

		String content = mapper.createObjectNode().put("name", testFoodName).put("quantity", testFoodQuantity)
				.put("foodType", testFoodTypeName).toString();

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isForbidden()).andReturn();

		mockMvc.perform(post(URL).header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isForbidden())
				.andReturn();

	}

	@Test
	public void testGetFoodByIdSuccess() throws Exception {

		MvcResult result = mockMvc
				.perform(get(URL + testFoodId).header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		Food food = mapper.readValue(result.getResponse().getContentAsString(), Food.class);

		System.out.println(food);

		assertEquals("bread", food.getName());
		assertEquals("fruit", food.getFoodType().getName());
	}

	@Test
	public void testUpdateFoodSuccess() throws Exception {
		Long newQuantity = 19L;
		String content = mapper.createObjectNode().put("name", testFoodName + testAddText).put("quantity", newQuantity)
				.put("foodType", testFoodTypeName + testAddText).toString();

		MvcResult result = mockMvc
				.perform(put(URL + testFoodId)
						.header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk()).andReturn();

		Food food = mapper.readValue(result.getResponse().getContentAsString(), Food.class);

		System.out.println(food);

		assertEquals(testFoodName + testAddText, food.getName());
		assertEquals(newQuantity, food.getQuantity());
		assertEquals(testFoodTypeName + testAddText, food.getFoodType().getName());

	}

	@Test
	public void testDeleteFoodByIdSuccess() throws Exception {

		mockMvc.perform(delete(URL + testFoodId)
				.header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		mockMvc.perform(get(URL + testFoodId).header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void testSearchByNameFoodSuccess() throws Exception {

		MvcResult result = mockMvc.perform(get(URL + "/search?name=" + testSearchFoodName)
				.header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		List<Food> foods = mapper.readValue(result.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Food.class));

		assertTrue(foods.size() >= 1);
		assertEquals(true, foods.stream().anyMatch(food -> {
			return food.getName().equalsIgnoreCase(testSearchFoodName);
		}));
	}

	@Test
	public void testSearchByTypeFoodSuccess() throws Exception {

		MvcResult result = mockMvc.perform(get(URL + "/search?type=" + testSearchFoodTypeName)
				.header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		List<Food> foods = mapper.readValue(result.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Food.class));

		assertTrue(foods.size() >= 1);
		assertEquals(true, foods.stream().anyMatch(food -> {
			return food.getFoodType().getName().equalsIgnoreCase(testSearchFoodTypeName);
		}));
	}
	
	@Test
	public void testSearchByDateAddedSuccess() throws Exception {
		
		// Create the Food to get the Date of creation;
		String content = mapper.createObjectNode().put("name", testFoodName).put("quantity", testFoodQuantity)
				.put("foodType", testFoodTypeName).toString();

		MvcResult result = mockMvc
				.perform(post(URL).header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_EMAIL, ADMIN_REGISTRATION_NUMBER))
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isCreated()).andReturn();

		Food newFood = mapper.readValue(result.getResponse().getContentAsString(), Food.class);

		DateFormat formatter = new SimpleDateFormat(dateFormat);

		String formattedDateStart = formatter.format(getStartOfDay(newFood.getCreatedAt()));
		String formattedDateEnd = formatter.format(getEndOfDay(newFood.getCreatedAt()));

			
		result = mockMvc.perform(get(URL + "/search?createdAtStart="+formattedDateStart+"&createdAtEnd="+formattedDateEnd)
				.header(HttpHeaders.AUTHORIZATION, getToken(EMAIL, REGISTRATION_NUMBER))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		List<Food> foods = mapper.readValue(result.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Food.class));

		assertTrue(foods.size() >= 1);
		assertEquals(true, foods.stream().anyMatch(food -> {
			return food.getFoodType().getName().equalsIgnoreCase(testSearchFoodTypeName);
		}));
	}
	
	private Date getStartOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 0, 0, 0);
	    return calendar.getTime();
	}
	
	private Date getEndOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 23, 59, 59);
	    return calendar.getTime();
	}

}
