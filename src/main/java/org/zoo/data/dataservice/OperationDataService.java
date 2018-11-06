package org.zoo.data.dataservice;

import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

public interface OperationDataService {

    void addAnimalToRoom(String roomName, Animal a) throws RoomDataServiceException, AnimalDataServiceException;
	
	void removeAnimalFromRoom(String roomName, Animal a) throws RoomDataServiceException,AnimalDataServiceException;
	
	void transferAnimalBetweenRoom(String oldRoomName,String newRoomName,Animal a)throws RoomDataServiceException,AnimalDataServiceException;

	void addFavoriteRoom(String animalName, Room r)throws AnimalDataServiceException,RoomDataServiceException;
	
	void removeFavoriteRoom(String animalName, Room r)throws AnimalDataServiceException, RoomDataServiceException;
}
