package org.zoo.data;



import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
public class AnimalDataServiceTest {

	@Autowired
	private AnimalDataService animalDataService;

	private Animal animal;

	@Before
	public void setUp() {
		Date d = new Date();
		animal = new Animal();
		animal.setAnimalName("Lion");
		animal.setCreateDate(new Timestamp(d.getTime()));
		animal.setPreference((long) 5);
		animal.setType(AnimalHappyType.LessThanOrEqual.getValue());
	}

	@Test
	public void testAdd() {
		Long id = animalDataService.addAnimal(animal);
		Assert.assertNotEquals(0, id.longValue());
	}

	@Test
	public void testGet() {
		Animal a = null;
		try {
			a = animalDataService.getAnimalByName("Lion");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(a);
	}
	
	@Test
	public void testUpdate()
	{
		Animal a = null;
		try {
			a = animalDataService.getAnimalByName("Lion");
		    a.setAnimalName("Tiger");
		    a.setPreference(10L);
		    a.setType(AnimalHappyType.GreaterThan.getValue());
		    animalDataService.updateAnimal(a);
		    Assert.assertEquals(a.getAnimalId(),animalDataService.getAnimalById(a.getAnimalId()).getAnimalId());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	

}
