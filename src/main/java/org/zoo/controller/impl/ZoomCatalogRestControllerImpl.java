package org.zoo.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.controller.ZooCatalogRestController;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

@RestController
public class ZoomCatalogRestControllerImpl implements ZooCatalogRestController {

	@Autowired
	private ZooCatalogService zooCatalog;
	
	@Override
	public Long addAnimal(Animal a) {
		
		return zooCatalog.addAnimal(a);
	}

	@Override
	public Animal getAnimalById(Long animalId) {
		return zooCatalog.getAnimalById(animalId);
	}

	@Override
	public Animal getAnimalByName(String animalName) {
		return zooCatalog.getAnimalByName(animalName);
	}

	@Override
	public Integer updateAnimal(Animal a) {
		return zooCatalog.updateAnimal(a);
	}

	@Override
	public Integer deleteAnimal(String animalName) {
		Animal a = zooCatalog.getAnimalByName(animalName);
		return zooCatalog.deleteAnimal(a);
	}

	@Override
	public Long addRoom(Room r) {
		
		return zooCatalog.addRoom(r);
	}

	@Override
	public Room getRoomById(Long roomId) {
		
		return zooCatalog.getRoomById(roomId);
	}

	@Override
	public Room getRoomByTitle(String roomTitle) {
		
		return zooCatalog.getRoomByTitle(roomTitle);
	}

	@Override
	public Integer updateRoom(Room r) {
		
		return zooCatalog.updateRoom(r);
	}

	@Override
	public Integer deleteRoom(String roomName) {
		Room r = zooCatalog.getRoomByTitle(roomName);
		return zooCatalog.deleteRoom(r);
	}

}
