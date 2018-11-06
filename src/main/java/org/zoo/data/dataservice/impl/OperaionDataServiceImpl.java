package org.zoo.data.dataservice.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.OperationDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

@Service
public class OperaionDataServiceImpl implements OperationDataService {

	
	@Autowired
	private RoomDataService roomDataService;
	
	@Autowired
	private AnimalDataService animalDataService;
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW,rollbackFor= {RoomDataServiceException.class,AnimalDataServiceException.class})
	@Override
	public void addAnimalToRoom(String roomName, Animal a) throws RoomDataServiceException, AnimalDataServiceException {
	    Room ru = roomDataService.getRoomByTitle(roomName);
	    Animal ta = animalDataService.getAnimalByName(a.getAnimalName());
	    List<Animal> animals = new LinkedList<>();
	    animals = ru.getAnimalAssigns();
	    animals.add(ta);
	    Date d = new Date();
		ta.setLocatedDate(new Timestamp(d.getTime()));
	    ru.setCapacity(ru.getCapacity()-1);
	    ru.setAnimalAssigns(animals);
		
		
	}
	

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW,rollbackFor= {RoomDataServiceException.class,AnimalDataServiceException.class})
	@Override
	public void removeAnimalFromRoom(String roomName, Animal a) throws RoomDataServiceException,AnimalDataServiceException {
		 Room ru = roomDataService.getRoomByTitle(roomName);
		 
		 List<Animal> animals = new LinkedList<>();
		 animals = ru.getAnimalAssigns();
		 animals.remove(a);
		 ru.setCapacity(ru.getCapacity()+1);
		 ru.setAnimalAssigns(animals);
	}

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,rollbackFor= {RoomDataServiceException.class,AnimalDataServiceException.class})
	@Override
	public void transferAnimalBetweenRoom(String oldRoomName, String newRoomName, Animal a) throws RoomDataServiceException,AnimalDataServiceException {
		removeAnimalFromRoom(oldRoomName, a);
		try {
			addAnimalToRoom(newRoomName, a);
		} catch (AnimalDataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	@Override
	public void addFavoriteRoom(String animalName, Room r) throws AnimalDataServiceException {
		Animal a = animalDataService.getAnimalByName(animalName);
		List<Room> favRooms = new LinkedList<>();
		favRooms = a.getFavRooms();
		favRooms.add(r);
		//a.setFavRooms(favRooms);
	}

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	@Override
	public void removeFavoriteRoom(String animalName, Room r) throws AnimalDataServiceException,RoomDataServiceException {
		Animal a = animalDataService.getAnimalByName(animalName);
		List<Room> favRooms = new LinkedList<>();
		favRooms = a.getFavRooms();
		favRooms.remove(r);
		a.setFavRooms(favRooms);
		
	}

}
