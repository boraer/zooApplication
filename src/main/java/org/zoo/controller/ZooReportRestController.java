package org.zoo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zoo.data.entity.Animal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Zoo Report Rest API",description="Report API ",basePath="/report")
@RequestMapping(value="/report")
public interface ZooReportRestController {

	@ApiOperation(value = "Get All Animal At Zoo Order By Name/CreateDate ASC/DESC  ",response=List.class)
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,value="/getAllAnimals")
    List<Animal> findAllAnimal(@RequestParam("sortBy")String sortBy,@RequestParam("orderBy")String orderBy);
	
	@ApiOperation(value = "Get All Animal In a Specific Room Order By Name/CreateDate ASC/DESC  ",response=List.class)
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,value="/getAllAnimalsInRoom")
	List<Animal> findAllAnimalInRoom(@RequestParam("roomName")String roomName,@RequestParam("sortBy")String sortBy,@RequestParam("orderBy")String orderBy);
    
	@ApiOperation(value = "Get An Animals' Favorite Room",response=List.class)
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,value="/getAnimalFavRooms")
	List<String> findFavaroiteRoomsForAnimal(@RequestParam("animalName")String animalName);
	
	@ApiOperation(value = "Get Happy Animal Report",response=Map.class)
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,value="/getHappyAnimalReport")
	Map<String,Long> happyAnimalReport();
}
