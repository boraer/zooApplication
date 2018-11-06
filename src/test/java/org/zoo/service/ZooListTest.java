package org.zoo.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;



@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
public class ZooListTest {

	@Autowired
	private ZooOperationService zooOps;

	@Autowired
	private ZooCatalogService zooCatalog;

	private Animal animal;

	private Room room;
	
	@Before
	public void setUp() {
		Date d = new Date();
		room = new Room();
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Red");
		zooCatalog.addRoom(room);
		d = new Date();
		room = new Room();
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Blue");
		zooCatalog.addRoom(room);
		d = new Date();	
		animal = new Animal();
		animal.setAnimalName("Tiger");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 5);
		animal.setType(AnimalHappyType.LessThanOrEqual.getValue());
		d = new Date();
		zooCatalog.addAnimal(animal);
		zooOps.addAnimalToRoom("Red", animal);
		d = new Date();
		animal = new Animal();
		animal.setAnimalName("Cat");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 15);
		animal.setType(AnimalHappyType.GreaterThanOrEqual.getValue());
		
		zooCatalog.addAnimal(animal); 
		zooOps.addAnimalToRoom("Red", animal);	
		d = new Date();
		animal = new Animal();
		animal.setAnimalName("Zebra");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 20);
		animal.setType(AnimalHappyType.GreaterThanOrEqual.getValue());
		
		zooCatalog.addAnimal(animal);
	
		zooOps.addFavRoomToAnimal("Zebra", "Red");
		zooOps.addFavRoomToAnimal("Zebra", "Blue");
		
	}
	
	@Test
	public void test()
	{
	
		
	List<Animal> asC = zooOps.findAllAnimal("located", "Desc");
	Assert.assertNotNull(asC);
	List<Animal> asNC = zooOps.findAllAnimalInRoom("Red", "createDate", "Asc");	
	Assert.assertNotNull(asNC);
	List<String> favRoomNames = zooOps.findFavaroiteRoomsForAnimal("Zebra");
	favRoomNames.forEach(System.out::println);
	}

}
