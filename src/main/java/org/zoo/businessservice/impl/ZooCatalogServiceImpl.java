package org.zoo.businessservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.businessservice.ZooCatalogService;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

@Service
public class ZooCatalogServiceImpl implements ZooCatalogService {

	@Autowired
	private RoomDataService roomDataService;

	@Autowired
	private AnimalDataService animalDataService;

	@Override
	public Long addAnimal(Animal a) {
	    
		return animalDataService.addAnimal(a);
	}

	@Override
	public Animal getAnimalById(Long id) {
		Animal a = new Animal();
		try {
			a = animalDataService.getAnimalById(id);
		} catch (Throwable e) {
			e.getMessage();
		}
		return a;
	}

	@Override
	public Animal getAnimalByName(String name) {
		Animal a = new Animal();
		try {
			a = animalDataService.getAnimalByName(name);
		} catch (Throwable e) {

			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Integer updateAnimal(Animal a) {
		Integer result = 0;
		try {
			animalDataService.updateAnimal(a);
			result = 1;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteAnimal(Animal a) {
		Integer result = 0;
		try {
			animalDataService.deleteAnimal(a);
			result = 1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public Long addRoom(Room r) {
		r.setCapacity(r.getSize());
		return roomDataService.addRoom(r);
	}

	@Override
	public Room getRoomById(Long id) {
		Room r = new Room();
		try {
			r = roomDataService.getRoomById(id);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Room getRoomByTitle(String title) {
		Room r = new Room();
		try {
			r = roomDataService.getRoomByTitle(title);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Integer updateRoom(Room r) {
		Integer result = 0;
		try {
			roomDataService.updateRoom(r);
			result = 1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteRoom(Room r) {
		Integer result = 0;
		try {
			roomDataService.deleteRoom(r);
			result = 1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
