package org.zoo.data.dataservice.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zoo.data.dataservice.RoomDataService;
import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;
import org.zoo.data.repository.RoomRepository;

import net.bytebuddy.agent.builder.AgentBuilder.Listener.Filtering;

@Service
public class RoomDataServiceImpl implements RoomDataService {

	@Autowired
	private RoomRepository roomRepo;

	@Transactional
	@Override
	public Long addRoom(Room r) {
		Long result = 0L;
		Optional<Room> rm = roomRepo.findByRoomTitle(r.getRoomTitle());

		if (!rm.isPresent() && r != null) {
			Date d = new Date();
			r.setCreateDate(new Timestamp(d.getTime()));
			Room rs = roomRepo.save(r);
			result = rs.getRoomId();
		}

		return result;
	}

	@ExceptionHandler(RoomDataServiceException.class)
	@Override
	public Room getRoomById(Long id) throws RoomDataServiceException {
		Optional<Room> r = roomRepo.findById(id);
		return r.orElseThrow(() -> new RoomDataServiceException("RoomNotFound"));
	}

	@Override
	public Room getRoomByTitle(String title) throws RoomDataServiceException {
		Optional<Room> r = roomRepo.findByRoomTitle(title);
		return r.orElseThrow(() -> new RoomDataServiceException("RoomNotFound"));
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateRoom(Room r) throws RoomDataServiceException {
		Room uR = getRoomById(r.getRoomId());
		uR.setCapacity(r.getCapacity());
		uR.setCreateDate(r.getCreateDate());
		uR.setRoomTitle(r.getRoomTitle());
		uR.setSize(r.getSize());
	}

	@Override
	public void deleteRoom(Room r) {
		roomRepo.delete(r);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	@Override
	public List<Animal> animalsInRoomByNameDesc(String roomName) throws RoomDataServiceException {
		Room r = getRoomByTitle(roomName);
		List<Animal> l = new LinkedList<>();
		l = r.getAnimalAssigns().stream().sorted(Comparator.comparing(Animal::getAnimalName).reversed())
				.collect(Collectors.toList());
		return l;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	@Override
	public List<Animal> animalsInRoomByNameAsc(String roomName) throws RoomDataServiceException {
		Room r = getRoomByTitle(roomName);
		List<Animal> l = new LinkedList<>();
		l = r.getAnimalAssigns().stream().sorted(Comparator.comparing(Animal::getAnimalName))
				.collect(Collectors.toList());
		return l;
	}

	@Override
	public List<String> favoriteRoomNamesForAnimal(Animal animal) {
		List<String> ls = new LinkedList<>();
		ls = roomRepo.findFavoriteRoomsForAnimal(animal);
		return ls;
	}

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW)
	@Override
	public Map<String,Long> happyAnimalReport() {
		
		Iterable<Room> rooms = roomRepo.findAll();
	    Map<String,Long> report = new HashMap<>();
     for(Room r:rooms)
     {
    	
		Long c =  r.getAnimalAssigns().stream().filter(a->{
            boolean factor=false;
                 switch (AnimalHappyType.getEnum(a.getType())) {
                     case GreaterThanOrEqual:
                    	 if(a.getPreference()<=r.getCapacity())
                    		 factor= true;
                    	 break;
                     case GreaterThan:
                    	 if(a.getPreference()<r.getCapacity())
                    		 factor= true;
                    	  break;
                     case LessThan:
                    	 if(r.getCapacity()<a.getPreference())
                    		 factor = true;
                    	 break;
                     case LessThanOrEqual:
                    	 if(r.getCapacity()<=a.getPreference())
                    		 factor = true;
                    	 break;
                 }

                 return factor;
		}).collect(Collectors.counting());
	  report.put(r.getRoomTitle(), c);
	  
	 }
     return report;
	}
}

/*
 * 
 * Long c = StreamSupport.stream(rooms.spliterator(), false)
 * 
 * .forEach(r->r.getAnimalAssigns().stream().filter(a->{ boolean factor=false;
 * switch (AnimalHappyType.getEnum(a.getType())) { case GreaterThanOrEqual:
 * if(a.getPreference()<=r.getCapacity()) factor= true; break; case
 * LessThanOrEqual: if(r.getCapacity()<=a.getPreference()) factor = true; break;
 * }
 * 
 * return factor; }).collect(Collectors.counting()));
 */
