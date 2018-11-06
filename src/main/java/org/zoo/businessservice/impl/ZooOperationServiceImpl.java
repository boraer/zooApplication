package org.zoo.businessservice.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.businessservice.OrderType;
import org.zoo.businessservice.SortType;
import org.zoo.businessservice.ZooOperationService;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.OperationDataService;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

@Service
public class ZooOperationServiceImpl implements ZooOperationService {

	@Autowired
	private RoomDataService roomDataService;

	@Autowired
	private AnimalDataService animalDataService;

	@Autowired
	private OperationDataService operationDataService;

	@Override
	public Integer addAnimalToRoom(String roomName, Animal a) {
		Integer result = 0;
		try {
			Room r = roomDataService.getRoomByTitle(roomName);
			if (0 < r.getCapacity()) {
				operationDataService.addAnimalToRoom(roomName, a);

				result = 1;
			} else// not enough place for animal
				result = 2;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer removeAnimalFromRoom(String roomName, Animal a) {
		Integer result = 0;
		try {
			Room r = roomDataService.getRoomByTitle(roomName);
			operationDataService.removeAnimalFromRoom(roomName, a);
			a.setLocatedDate(null);
			animalDataService.updateAnimal(a);
			result = 1;
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer transferAnimalBetweenRoom(String oldRoomName, String newRoomName, Animal a) {
		Integer result = 0;
		Room rNew;
		try {
			rNew = roomDataService.getRoomByTitle(newRoomName);
			if (0 < rNew.getCapacity()) {
				operationDataService.transferAnimalBetweenRoom(oldRoomName, newRoomName, a);
				result = 1;
			} else
				result = 2;
		} catch (Throwable e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer addFavRoomToAnimal(String animalName, String roomName) {
		Integer result = 0;
		Room r;
		try {
			r = roomDataService.getRoomByTitle(roomName);
			operationDataService.addFavoriteRoom(animalName, r);
			result = 1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer removeFavRoomFromAnimal(String animalName, String roomName) {
		Integer result = 0;
		Room r;
		try {
			r = roomDataService.getRoomByTitle(roomName);
			operationDataService.removeFavoriteRoom(animalName, r);
			result = 1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Animal> findAllAnimal(String sortBy, String orderBy) {
		List<Animal> ls = new LinkedList<>();
		switch (SortType.getEnum(sortBy)) {
		case Sort_By_Name:
			if (OrderType.Order_By_Asc == OrderType.getEnum(orderBy))
				ls = animalDataService.findAllAnimalByNameAsc();
			else
				ls = animalDataService.findAllAnimalByNameDesc();
			break;
		case Sort_By_Create_Date:
			if (OrderType.Order_By_Asc == OrderType.getEnum(orderBy))
				ls = animalDataService.findAllAnimalByCreateDateAsc();
			else
				ls = animalDataService.findAllAnimalByCreateDateDesc();
			break;
		default:
			ls = animalDataService.findAllAnimalByNameAsc();
			break;
		}
		return ls;
	}

	@Override
	public List<Animal> findAllAnimalInRoom(String roomName, String sortBy, String orderBy) {
		List<Animal> an = new LinkedList<>();
		try {
			Room r = roomDataService.getRoomByTitle(roomName);
			switch (SortType.getEnum(sortBy)) {
			case Sort_By_Name:
				if (OrderType.Order_By_Asc == OrderType.getEnum(orderBy))
					an = roomDataService.animalsInRoomByNameAsc(r.getRoomTitle());
				else
					an = roomDataService.animalsInRoomByNameDesc(r.getRoomTitle());
				break;
			case Sort_By_Create_Date:
				if (OrderType.Order_By_Asc == OrderType.getEnum(orderBy))
					an = animalDataService.findAllAnimalInRoomByCreateDateAsc(r);
				else
					an = animalDataService.findAllAnimalInRoomByCreateDateDesc(r);
				break;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return an;
	}

	@Override
	public List<String> findFavaroiteRoomsForAnimal(String animalName) {
		List<String> ls = new LinkedList<>();
		try {
			// Animal a = animalDataService.getAnimalByName(animalName);
			ls = animalDataService.findAnimalFavoriteRooms(animalName);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public Map<String, Long> getHappyAnimalReportByRoom() {

		return roomDataService.happyAnimalReport();
	}

}
