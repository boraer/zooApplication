package org.zoo.controller.integration;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource(locations = "classpath:application-test.yml")
@Profile(value = "test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZooRestControllerIntegrationTest {

	private MockMvc mockMvc;

	/*
	@Autowired
	private ZooCatalogRestController restController;
*/
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private ZooCatalogService zooCatalog;
	
	private Animal animal;

	private Room room;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		//this.mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
	}

	@Test
	public void test0() throws Exception {
		Date d = new Date();
		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < 10; i++) {
			
			Animal a = new Animal("Tiger" + i, AnimalHappyType.LessThan.getValue(), new Long((i + 1) * 3));
			String s = mapper.writeValueAsString(a);
			mockMvc.perform(
					post("/catalog/animal/addAnimal").content(s).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(status().isOk()).andReturn();

		}
	}

	@Test
	public void test1() throws Exception {
	 MvcResult res=	mockMvc.perform(get("/catalog/animal/name/Tiger1")).andExpect(status().isOk()).andDo(print()).andReturn();
   
	}

	
	@Test
	public void test2() throws Exception
	{
		Date d = new Date();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0;i<5;i++)
		{
			Room r = new Room();
			r.setCapacity(10L);
			r.setSize(10L);
			r.setRoomTitle("Magenta"+i);
			String s = mapper.writeValueAsString(r);
			mockMvc.perform(
					post("/catalog/room/addRoom").content(s).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(status().isOk()).andReturn();
		}
	}
		
		@Test
		public void test3()  throws Exception
		{
			 MvcResult res=	mockMvc.perform(get("/catalog/room/name/Magenta3")).andExpect(status().isOk()).andDo(print()).andReturn();
		}
	
		@Test
		public void test4()  throws Exception
		{
			Date d = new Date();
			
			Animal a = zooCatalog.getAnimalById(2L);
			Room r = zooCatalog.getRoomByTitle("Magenta3");
		
			mockMvc.perform(
					post("/operation/animal/addFavoriteRoom").param("animalName", a.getAnimalName()).param("roomName", r.getRoomTitle()))
					.andExpect(status().isOk()).andReturn();
		}
		
		
		@Test
		public void test5()  throws Exception
		{
			Date d = new Date();
			ObjectMapper mapper = new ObjectMapper();
			
			Room r = zooCatalog.getRoomByTitle("Magenta3");
			for(int i=0;i<4;i++) {
			Animal a = zooCatalog.getAnimalById(2L);
		    String s = mapper.writeValueAsString(a);
			mockMvc.perform(
					post("/operation/room/{roomName}/addAnimalToRoom",r.getRoomTitle()).content(s).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(status().isOk()).andReturn();
			}
		}
		
		@Test
		public void test6() throws Exception
		{
			mockMvc.perform(
					get("/report/getAllAnimalsInRoom").param("roomName", "Magenta3").param("sortBy","name").param("orderBy","Desc"))
					.andExpect(status().isOk()).andDo(print()).andReturn();
			
		}
		
		@Test
		public void test7() throws Exception
		{
			mockMvc.perform(
					get("/report/getAllAnimals").param("sortBy","createDate").param("orderBy","Asc"))
					.andExpect(status().isOk()).andDo(print()).andReturn();
			
		}
		
		@Test
		public void test8() throws Exception
		{
			Animal a = zooCatalog.getAnimalById(2L);
			mockMvc.perform(
					get("/report/getAnimalFavRooms").param("animalName",a.getAnimalName()))
					.andExpect(status().isOk()).andDo(print()).andReturn();
			
		}
		
		@Test
		public void test9()  throws Exception
		{
			Date d = new Date();
			
			Animal a = zooCatalog.getAnimalById(2L);
			Room r = zooCatalog.getRoomByTitle("Magenta3");
		    
			mockMvc.perform(
					post("/operation/animal/removeFavoriteRoom").param("animalName", a.getAnimalName()).param("roomName", r.getRoomTitle()))
					.andExpect(status().isOk()).andReturn();
		}
		
		
}
