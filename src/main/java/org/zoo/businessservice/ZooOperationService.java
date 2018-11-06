package org.zoo.businessservice;

import java.util.List;
import java.util.Map;

import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

public interface ZooOperationService {

	Integer addAnimalToRoom(String roomName, Animal a);
	
	Integer removeAnimalFromRoom(String roomName, Animal a) ;
	
	Integer transferAnimalBetweenRoom(String oldRoomName,String newRoomName,Animal a);

	Integer addFavRoomToAnimal(String animalName,String roomName);
	
	Integer removeFavRoomFromAnimal(String animalName,String roomName);
	
    List<Animal> findAllAnimal(String sortBy,String orderBy);
	
	List<Animal> findAllAnimalInRoom(String roomName,String sortBy,String orderBy);
    
	List<String> findFavaroiteRoomsForAnimal(String animalName);
	
	Map<String,Long> getHappyAnimalReportByRoom();
}
