package org.zoo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.businessservice.impl.ZooCatalogServiceImpl;
import org.zoo.businessservice.impl.ZooOperationServiceImpl;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.dataservice.impl.AnimalDataServiceImpl;
import org.zoo.data.dataservice.impl.RoomDataServiceImpl;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
public class ZooFavOperationTest {

	
	
	@Autowired
	private ZooOperationService zooOps;

	@Autowired
	private ZooCatalogService zooCatalog;

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
	 	
	 	zooCatalog.addAnimal(animal);
		d = new Date();

		animal = new Animal();
		animal.setAnimalName("Lion");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 15);
		animal.setType(AnimalHappyType.GreaterThanOrEqual.getValue());
		zooCatalog.addAnimal(animal);

		d = new Date();

		
		room = new Room();
		room.setSize(10L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Red");
		zooCatalog.addRoom(room);
		
		d = new Date();
		room = new Room();
		room.setSize(15L);
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Blue");
		zooCatalog.addRoom(room);

	}

	@Test
	public void test() {
		int r = 0;
	
		r = zooOps.addFavRoomToAnimal("Lion", "Blue");
		Assert.assertEquals(1, r);

		
	}

}
