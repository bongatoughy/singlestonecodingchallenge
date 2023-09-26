package com.kyle.singlestonecodingchallenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SinglestonecodingchallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ContactRepository service;

	@Test
	public void testGetContacts() throws Exception {
		mockMvc.perform(get("/contacts"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Bilbo"));
	}

	@Test
	public void testCreateContact() throws Exception {
		mockMvc.perform((post("/contacts" )).contentType(MediaType.APPLICATION_JSON)
						.content(
								"{\"name\": {" +
										"\"first\": \"John\", " +
										"\"middle\": \"King\", " +
										"\"last\": \"Smith\" " +
										"}, " +
										"\"address\": {" +
										"\"street\": \"elm\"," +
										"\"city\": \"houston\"," +
										"\"state\": \"TX\"," +
										"\"zip\": \"33333\"" +
										"}, " +
										"\"phone\": {" +
										"\"number\": \"123\"," +
										"\"type\": \"home\"" +
										"}, " +
										"\"email\":  \"john@john.com\"" +
										"}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.middleName").value("King"))
				.andExpect(jsonPath("$.lastName").value("Smith"))
				.andExpect(jsonPath("$.street").value("elm"))
				.andExpect(jsonPath("$.city").value("houston"))
				.andExpect(jsonPath("$.state").value("TX"))
				.andExpect(jsonPath("$.zip").value("33333"))
				.andExpect(jsonPath("$.phone").value("123"))
				.andExpect(jsonPath("$.phoneType").value("home"))
				.andExpect(jsonPath("$.email").value("john@john.com"));
	}

	@Test
	@DirtiesContext
	public void testUpdateContact() throws Exception {
		mockMvc.perform((put("/contacts/1" )).contentType(MediaType.APPLICATION_JSON)
						.content(
								"{" +
										"\"name\": {" +
										"\"first\": \"Richard\", " +
										"\"middle\": \"Lion\", " +
										"\"last\": \"Heart\" " +
										"}, " +
										"\"address\": {" +
										"\"street\": \"ash\"," +
										"\"city\": \"miami\"," +
										"\"state\": \"FL\"," +
										"\"zip\": \"44444\"" +
										"}, " +
										"\"phone\": {" +
										"\"number\": \"456\"," +
										"\"type\": \"work\"" +
										"}, " +
										"\"email\":  \"richard@john.com\"" +
										"}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Richard"))
				.andExpect(jsonPath("$.middleName").value("Lion"))
				.andExpect(jsonPath("$.lastName").value("Heart"))
				.andExpect(jsonPath("$.street").value("ash"))
				.andExpect(jsonPath("$.city").value("miami"))
				.andExpect(jsonPath("$.state").value("FL"))
				.andExpect(jsonPath("$.zip").value("44444"))
				.andExpect(jsonPath("$.phone").value("456"))
				.andExpect(jsonPath("$.phoneType").value("work"))
				.andExpect(jsonPath("$.email").value("richard@john.com"));

		mockMvc.perform(get("/contacts"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Richard"))
				.andExpect(jsonPath("$[0].middleName").value("Lion"))
				.andExpect(jsonPath("$[0].lastName").value("Heart"))
				.andExpect(jsonPath("$[0].street").value("ash"))
				.andExpect(jsonPath("$[0].city").value("miami"))
				.andExpect(jsonPath("$[0].state").value("FL"))
				.andExpect(jsonPath("$[0].zip").value("44444"))
				.andExpect(jsonPath("$[0].phone").value("456"))
				.andExpect(jsonPath("$[0].phoneType").value("work"))
				.andExpect(jsonPath("$[0].email").value("richard@john.com"));
	}

	@Test
	public void testGetContact() throws Exception {
		mockMvc.perform(get("/contacts/1" ))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Bilbo"));
	}


	@Test
	@DirtiesContext
	public void testDeleteContact() throws Exception {
		mockMvc.perform(delete("/contacts/1" ))
				.andDo(print())
				.andExpect(status().isOk());
		mockMvc.perform(get("/contacts"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Frodo"));
	}

	@Test
	public void testCallList() throws Exception {
		mockMvc.perform(get("/call-list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name.first").value("Bilbo"));
	}
}