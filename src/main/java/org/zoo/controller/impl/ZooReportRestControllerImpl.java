package org.zoo.controller.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.controller.ZooReportRestController;
import org.zoo.data.entity.Animal;

@RestController
public class ZooReportRestControllerImpl implements ZooReportRestController {

	@Autowired
	private ZooOperationService zooOperation;

	@Override
	public List<Animal> findAllAnimal(String sortBy, String orderBy) {

		return zooOperation.findAllAnimal(sortBy, orderBy);
	}

	@Override
	public List<Animal> findAllAnimalInRoom(String roomName, String sortBy, String orderBy) {

		return zooOperation.findAllAnimalInRoom(roomName, sortBy, orderBy);
	}

	@Override
	public List<String> findFavaroiteRoomsForAnimal(String animalName) {

		return zooOperation.findFavaroiteRoomsForAnimal(animalName);
	}

	@Override
	public Map<String, Long> happyAnimalReport() {
		
		return zooOperation.getHappyAnimalReportByRoom();
	}

}
