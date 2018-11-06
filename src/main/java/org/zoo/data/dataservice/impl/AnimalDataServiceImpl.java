package org.zoo.data.dataservice.impl;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zoo.data.dataservice.AnimalDataService;
import org.zoo.data.dataservice.exception.AnimalDataServiceException;
import org.zoo.data.dataservice.exception.RoomDataServiceException;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.AnimalHappyType;
import org.zoo.data.entity.Room;
import org.zoo.data.repository.AnimalRepository;

@Service
public class AnimalDataServiceImpl implements AnimalDataService {

	@Autowired
	private AnimalRepository animalRepo;
	
	@Transactional
	@Override
	public Long addAnimal(Animal a) {
		Optional<Animal> as = animalRepo.findByAnimalName(a.getAnimalName());
		Long result = 0L;
		if(!as.isPresent())
		{
		if(a!=null) {
		Date d = new Date();
		a.setCreateDate(new Timestamp(d.getTime()));
		AnimalHappyType type = AnimalHappyType.getEnum(a.getType());
		a.setType(type.getValue());
		Animal b = animalRepo.save(a);
		result =  b.getAnimalId();
		}
		else
		result =  -1L;
		}
		else
		{
		result = -2L;	
		}
		return result;
	}

	@Override
	public Animal getAnimalById(Long id) throws AnimalDataServiceException {
		Optional<Animal> animal = animalRepo.findById(id);		
	   return animal.orElseThrow(()->new AnimalDataServiceException("AnimalNotFound"));
	}

	@Override
	public Animal getAnimalByName(String name) throws AnimalDataServiceException {
		Optional<Animal> animal =  animalRepo.findByAnimalName(name);
		  return animal.orElseThrow(()->new AnimalDataServiceException("AnimalNotFound"));
	}

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW)
	@Override
	public void updateAnimal(Animal a) throws AnimalDataServiceException {
	 Animal uA = getAnimalById(a.getAnimalId());
     uA.setAnimalName(a.getAnimalName());
     uA.setCreateDate(a.getCreateDate());
     uA.setPreference(a.getPreference());
     uA.setLocatedDate(a.getLocatedDate());
     uA.setType(a.getType());
	}

	
	@Override
	public void deleteAnimal(Animal a) throws AnimalDataServiceException {
		animalRepo.delete(a);
		
	}

	

	@Override
	public List<Animal> findAllAnimalByNameAsc() {
		
		return animalRepo.findAllByOrderByAnimalNameAsc();
	}

	@Override
	public List<Animal> findAllAnimalByNameDesc() {
	
		return animalRepo.findAllByOrderByAnimalNameDesc();
	}

	@Override
	public List<Animal> findAllAnimalByCreateDateAsc() {
		
		return animalRepo.findAllAnimalByCreateAsc();
	}

	@Override
	public List<Animal> findAllAnimalByCreateDateDesc() {
		
		return animalRepo.findAllAnimalByCreateDesc();
	}

	@Override
	public List<Animal> findAllAnimalInRoomByCreateDateAsc(Room r) {
		
		return animalRepo.findAllAnimalInRoomByCreateAsc(r);
	}

	@Override
	public List<Animal> findAllAnimalInRoomByCreateDateDesc(Room r) {
		
		return animalRepo.findAllAnimalInRoomByCreateDesc(r);
	}

	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	@Override
	public List<String> findAnimalFavoriteRooms(String animalName) {
		List<String> al = new LinkedList<>();
		try {
			Animal a = getAnimalByName(animalName);
			al = a.getFavRooms().stream().map(r->r.getRoomTitle()).collect(Collectors.toList());
		} catch (AnimalDataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
    /*
	private void happyReport()
	{
		
	 Iterable<Animal> aList =	animalRepo.findAll();
	 Map<String,List<Animal>> list = 
	 StreamSupport.stream(aList.spliterator(), false)
	 .collect(Collectors.groupingBy(a->a.getRoom().getRoomTitle()))
	 .entrySet().stream().filter(a->{
			boolean factor=false;
			switch (AnimalHappyType.getEnum(a.getType())) {
			case GreaterThanOrEqual:
			if(a.getPreference()<=r.getCapacity())
			factor= true;
				break;
			case LessThanOrEqual:
	        if(r.getCapacity()<=a.getPreference())
	        factor = true;
				break;
			}
			
			return factor;
		}).collect(Collectors.groupingBy(classifier))
	}*/


}
