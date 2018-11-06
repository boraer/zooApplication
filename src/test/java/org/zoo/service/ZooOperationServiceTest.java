package org.zoo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.OperationDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile(value="test")
public class ZooOperationServiceTest {

	@MockBean
	private RoomDataService roomDataService; 
	
	@MockBean
	private AnimalDataService animalDataService;
	
	@MockBean
	private OperationDataService operationDataService;
	
	@Autowired
	private ZooOperationService zooOps;


	private Animal animal;

	private Room room;

	@Before
	public void setUp() {
		Date d = new Date();
		animal = new Animal();
		
		animal.setAnimalName("Tiger");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 5);
		animal.setType(AnimalHappyType.LessThanOrEqual.getValue());
		

		room = new Room();
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Red");
		room.setCapacity(5L);
	}

	@Test
	public void test() throws Throwable {
      
		doNothing().when(operationDataService).addAnimalToRoom("Red", animal);
		doNothing().when(operationDataService).removeAnimalFromRoom("Red", animal);
		when(roomDataService.getRoomByTitle("Red")).thenReturn(room);
		Integer r = -1;
		r = zooOps.addAnimalToRoom("Red", animal);
		Assert.assertEquals(1,r.intValue());
		r = zooOps.removeAnimalFromRoom("Red", animal);
		Assert.assertEquals(1,r.intValue());
	}

}
