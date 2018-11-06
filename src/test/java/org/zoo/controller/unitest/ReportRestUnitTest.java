package org.zoo.controller.unitest;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.zoo.businessservice.ZooOperationService;
import org.zoo.controller.ZooOperationRestController;
import org.zoo.controller.ZooReportRestController;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportRestUnitTest {

	private MockMvc mockMvc;

	@Autowired
	private ZooReportRestController restController;

	@MockBean
	private ZooOperationService zooOperation;

	private List<Animal> animal = new LinkedList<>();

	private List<Room> room = new LinkedList<>();
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
		Date d = new Date();
		for (int i = 0; i < 10; i++) {
		 	animal.add(new Animal(new Long(i+1),"Tiger"+i, new Timestamp(d.getTime()),AnimalHappyType.LessThan.getValue(), new Long((i+1)*3), null, null));
		    room.add(new Room(new Long(i+1), "Yellow"+i, new Long((i+1)*6), new Long((i+1)*6), new Timestamp(d.getTime()), null));
		}
		
		
	}
	
	@Test
	public void testGetAllAnimals() throws Exception {
		when(zooOperation.findAllAnimal("name", "Desc")).thenReturn(animal);
		mockMvc.perform(get("/report/getAllAnimals").contentType(MediaType.APPLICATION_JSON).param("sortBy", "name").param("orderBy", "Desc"))
		  .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(animal.size()))).andDo(print()).andReturn();
	}
   
	@Test
	public void testGetAllAnimalsInRoom() throws Exception {
		when(zooOperation.findAllAnimalInRoom("Yellow","createDate", "Asc")).thenReturn(animal);
		mockMvc.perform(get("/report/getAllAnimalsInRoom").contentType(MediaType.APPLICATION_JSON).param("roomName", "Yellow").param("sortBy", "createDate").param("orderBy", "Asc"))
		  .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(animal.size()))).andDo(print()).andReturn();
	}
	
	@Test
	public void testGetFavRoomsOfAnimal() throws Exception
	{
		when(zooOperation.findFavaroiteRoomsForAnimal("Tiger1")).thenReturn(room.stream().map(e->e.getRoomTitle()).collect(Collectors.toList()));
		mockMvc.perform(get("/report/getAnimalFavRooms").contentType(MediaType.APPLICATION_JSON).param("animalName", "Tiger1"))
		  .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(room.size()))).andDo(print()).andReturn();
	
	}

}
