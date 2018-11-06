package org.zoo.controller.unitest;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.controller.ZooCatalogRestController;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogRestUnitTest {

	private MockMvc mockMvc;

	@Autowired
	private ZooCatalogRestController restController;

	@MockBean
	private ZooCatalogService zooCatalog;

	private Animal animal;

	private Room room;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
		Date d = new Date();
		animal = new Animal();
		animal.setAnimalName("Tiger");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 5);
		animal.setType(AnimalHappyType.LessThanOrEqual.getValue());

		room = new Room();
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Blue");
	
	}

	@Test
	public void testAddAnimal() throws Exception {
		when(zooCatalog.addAnimal(animal)).thenReturn(5L);
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(animal);
		mockMvc.perform(post("/catalog/animal/addAnimal").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(s))
				.andExpect(status().isOk()).andExpect(jsonPath("$", is(5))).andDo(print()).andReturn();

	}

	@Test
	public void testUpdateAnimal() throws Exception
	{
		when(zooCatalog.updateAnimal(animal)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		animal.setAnimalName("Zebra");
		String s = mapper.writeValueAsString(animal);
		mockMvc.perform(post("/catalog/animal/updateAnimal").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(s))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",is(1)))
		 .andDo(print()).andReturn();
	}
	
	@Test
	public void testGetAnimal() throws Exception {
		when(zooCatalog.getAnimalById(1L)).thenReturn(animal);
		mockMvc.perform(get("/catalog/animal/id/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.animalName", is("Tiger"))).andDo(print()).andReturn();

	}

	@Test
	public void testAddRoom() throws Exception
	{
		when(zooCatalog.addRoom(room)).thenReturn(new Long(10));
		mockMvc.perform(post("/catalog/room/addRoom").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest()).andDo(print()).andReturn();
	}
	
	@Test
	public void deleteRoom() throws Exception
	{
		when(zooCatalog.deleteRoom(room)).thenReturn(0);
		ObjectMapper mapper = new ObjectMapper();
		
		mockMvc.perform(post("/catalog/room/deleteRoom").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("roomName", "Blue"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",is(0)))
		 .andDo(print()).andReturn();
	}
}
