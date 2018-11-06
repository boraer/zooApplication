package org.zoo.data.dataservice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

public interface RoomDataService {

	Long addRoom(Room r);
	
	Room getRoomById(Long id) throws RoomDataServiceException;
	
	Room getRoomByTitle(String title)throws RoomDataServiceException;
	
	void updateRoom(Room r) throws RoomDataServiceException;
	
	void deleteRoom(Room r);

	
    List<Animal> animalsInRoomByNameDesc(String roomName) throws RoomDataServiceException;
    
    List<Animal> animalsInRoomByNameAsc(String roomName) throws RoomDataServiceException;
    
    List<String> favoriteRoomNamesForAnimal(Animal animal);
    
    Map<String,Long> happyAnimalReport(); 
}
