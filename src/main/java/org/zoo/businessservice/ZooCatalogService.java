package org.zoo.businessservice;

import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

public interface ZooCatalogService {

    Long addAnimal(Animal a);
    
	Animal getAnimalById(Long id);
	
	Animal getAnimalByName(String name) ;
	
	Integer updateAnimal(Animal a);
	
	Integer deleteAnimal(Animal a);
	
	
    Long addRoom(Room r);
	
	Room getRoomById(Long id);
	
	Room getRoomByTitle(String title);
	
	Integer updateRoom(Room r);
	
	Integer deleteRoom(Room r);
}
