package org.zoo.data;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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
import org.zoo.data.dataservice.OperationDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HappyReportTest {

	@Autowired
	private OperationDataService operationData;
	
	@Autowired
	private RoomDataService roomDataService;
	
	@Autowired
	private AnimalDataService animalDataService;

	@Before
	public void setUp() throws RoomDataServiceException, AnimalDataServiceException
	{
		Room r = new Room();
		r.setSize(10L);
		r.setCapacity(10L);
		r.setRoomTitle("Black");
	    roomDataService.addRoom(r);
	    
		for (int i = 1; i <= 10; i++) {
		Animal a = new Animal();
		a.setAnimalName("Animal"+i);
		a.setPreference(new Long((i*10)/2));
		a.setType(i%2==0?AnimalHappyType.GreaterThanOrEqual.getValue():AnimalHappyType.LessThanOrEqual.getValue());
		animalDataService.addAnimal(a);
		operationData.addAnimalToRoom("Black", a);
		}
		
	}
	
	@Test
	public void test() throws RoomDataServiceException {
 
	 Map<String, Long> report =   roomDataService.happyAnimalReport();	
	 
	}

}
