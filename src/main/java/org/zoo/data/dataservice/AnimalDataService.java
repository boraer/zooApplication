package org.zoo.data.dataservice;

import java.util.List;

import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

public interface AnimalDataService {

	Long addAnimal(Animal a);
    
	Animal getAnimalById(Long id) throws AnimalDataServiceException;
	
	Animal getAnimalByName(String name) throws AnimalDataServiceException;
	
	void updateAnimal(Animal a) throws AnimalDataServiceException;
	
	void deleteAnimal(Animal a) throws AnimalDataServiceException;
	

	
	List<Animal> findAllAnimalByNameAsc();
	
	List<Animal> findAllAnimalByNameDesc();
	
	List<Animal> findAllAnimalByCreateDateAsc();
	
	List<Animal> findAllAnimalByCreateDateDesc();
	
	List<Animal> findAllAnimalInRoomByCreateDateAsc(Room r);
	
	List<Animal> findAllAnimalInRoomByCreateDateDesc(Room r);
	
	List<String> findAnimalFavoriteRooms(String animalName);
}
