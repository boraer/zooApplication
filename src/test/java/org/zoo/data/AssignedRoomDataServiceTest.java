package org.zoo.data;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.entity.Animal;

import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
public class AssignedRoomDataServiceTest {

	
	@Autowired
	private AnimalDataService animalDataService;
	
	@Autowired
	private RoomDataService roomDataService;

	private Animal animal;
	
	private Room room;
	
	@Before
	public void setUp()
	{
		Date d = new Date();
		animal = new Animal();
		animal.setAnimalName("Lion");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 5);
		animal.setType(AnimalHappyType.LessThanOrEqual.getValue());
		animalDataService.addAnimal(animal);
		
		
		room = new Room();
		room.setCapacity(10L);
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Yellow");
		roomDataService.addRoom(room);
	}
	
	
	@Test
	public void testAdd() {
		Animal a = null;
		Room r = null;
		Long id = 0L;
		try {
			a = animalDataService.getAnimalByName("Lion");
			r = roomDataService.getRoomByTitle("Yellow");
	//		roomDataService.addAnimalToRoom("Yellow", a);
            Assert.assertTrue(true);
		} catch (Throwable e) {
			
			e.printStackTrace();
			  Assert.assertTrue(false);
		}

	}
	
}
