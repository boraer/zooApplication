package org.zoo.controller.unitest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.controller.ZooCatalogRestController;
import org.zoo.controller.ZooOperationRestController;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRestUnitTest {

	private MockMvc mockMvc;

	@Autowired
	private ZooOperationRestController restController;

	@MockBean
	private ZooOperationService zooOperation;

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
	public void testAddToRoom() throws Exception {
	  when(zooOperation.addAnimalToRoom(room.getRoomTitle(), animal)).thenReturn(1);
	 ObjectMapper mapper = new ObjectMapper();
	 String s = mapper.writeValueAsString(animal);
	  mockMvc.perform(post("/operation/room/"+room.getRoomTitle()+"/addAnimalToRoom").contentType(MediaType.APPLICATION_JSON).content(s))
	  .andExpect(status().isOk()).andExpect(jsonPath("$", is(1))).andDo(print()).andReturn();
	  
	}

	@Test
	public void testRemoveFromRoom() throws Exception
	{
		 when(zooOperation.removeAnimalFromRoom(room.getRoomTitle(), animal)).thenReturn(1);
		 ObjectMapper mapper = new ObjectMapper();
		 String s = mapper.writeValueAsString(animal);
		  mockMvc.perform(post("/operation/room/"+room.getRoomTitle()+"/removeAnimalFromRoom").contentType(MediaType.APPLICATION_JSON).content(s))
		  .andExpect(status().isOk()).andExpect(jsonPath("$", is(1))).andDo(print()).andReturn();
		  
	}
	
	@Test
	public void testTransferAnimal() throws Exception
	{
		
		 when(zooOperation.transferAnimalBetweenRoom("Blue", "Black", animal)).thenReturn(1);
		 ObjectMapper mapper = new ObjectMapper();
		 String s = mapper.writeValueAsString(animal);
		  mockMvc.perform(post("/operation/room/transferAnimal").contentType(MediaType.APPLICATION_JSON).content(s)
				  .param("animalName",animal.getAnimalName()).param("oldRoomName", "Blue").param("newRoomName", "Black"))
		  .andExpect(status().isOk()).andExpect(jsonPath("$", is(1))).andDo(print()).andReturn();
		   
	}
	
	@Test
	public void testAddAnimalFavRoom() throws Exception
	{
		
		 when(zooOperation.addFavRoomToAnimal("Honey Badger", "Yellow")).thenReturn(1);
		 
		  mockMvc.perform(post("/operation/animal/addFavoriteRoom").contentType(MediaType.APPLICATION_JSON).param("animalName", "Honey Badger").param("roomName", "Yellow"))
		  .andExpect(status().isOk()).andExpect(jsonPath("$", is(1))).andDo(print()).andReturn();
		   
	}
}
