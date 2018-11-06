package org.zoo.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.controller.ZooOperationRestController;
import org.zoo.data.entity.Animal;

@RestController
public class ZooOperationRestControllerImpl implements ZooOperationRestController {

	@Autowired
	private ZooOperationService zooOperation;

	@Override
	public Integer addAnimalToRoom(String roomName, @Valid Animal a) {

		return zooOperation.addAnimalToRoom(roomName, a);
	}

	@Override
	public Integer removeAnimalFromRoom(String roomName, @Valid Animal a) {

		return zooOperation.removeAnimalFromRoom(roomName, a);
	}

	@Override
	public Integer transferAnimalBetweenRoom(String oldRoomName, String newRoomName, @Valid Animal a) {

		return zooOperation.transferAnimalBetweenRoom(oldRoomName, newRoomName, a);
	}

	@Override
	public Integer addFavRoomToAnimal(String animalName, String roomName) {

		return zooOperation.addFavRoomToAnimal(animalName, roomName);
	}

	@Override
	public Integer removeFavRoomFromAnimal(String animalName, String roomName) {

		return zooOperation.removeFavRoomFromAnimal(animalName, roomName);
	}

}
