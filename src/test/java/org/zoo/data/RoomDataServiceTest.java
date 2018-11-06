package org.zoo.data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.entity.Room;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= "classpath:application-test.yml")
@Profile(value="test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomDataServiceTest {

	@Autowired
	private RoomDataService roomDataService;

	private Room room;

	@Before
	public void setUp() {
		room = new Room();
		room.setCapacity(10L);
		room.setSize(10L);
		Date d = new Date();
		room.setCreateDate(new Timestamp(d.getTime()));
		room.setRoomTitle("Purple");

	}

	@Test
	public void testAdd() {
		Long id = roomDataService.addRoom(room);
		Assert.assertNotNull(id);
	}

	@Test
	public void testGet() {
		Room ro = null;
		try {
			ro = roomDataService.getRoomByTitle("Purple");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		Assert.assertNotNull(ro);
	}

	@Test
	public void testUpdate() {
		Room ro = null;
		Room rm = null;

		try {
			ro = roomDataService.getRoomByTitle("Purple");
			ro.setCapacity(9L);
			roomDataService.updateRoom(ro);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		Assert.assertNotNull(ro);
		try {
			rm = roomDataService.getRoomByTitle("Purple");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(rm);
		Assert.assertEquals(ro.getCapacity(), rm.getCapacity());
	}

}
