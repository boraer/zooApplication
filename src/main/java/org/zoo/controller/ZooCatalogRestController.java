package org.zoo.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="Zoo Catalog Rest API",description="CRUD Operation API for Room and Animal",basePath="/catalog")
@RequestMapping(value="/catalog")
public interface ZooCatalogRestController {

	@ApiOperation(value = "Add Animal Rest Service",response=Long.class)
    @RequestMapping(value="/animal/addAnimal",method=RequestMethod.POST)
	@ResponseBody Long addAnimal(@Valid @RequestBody Animal a);
    
	@ApiOperation(value = "Get Animal By ID Rest Service",response=Animal.class)
    @RequestMapping(value="/animal/id/{animalId}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Animal getAnimalById(@PathVariable("animalId") Long animalId);
	
	@ApiOperation(value = "Get Animal By Name Rest Service",response=Animal.class)
    @RequestMapping(value="/animal/name/{animalName}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Animal getAnimalByName(@PathVariable("animalName")String animalName) ;
	
	@ApiOperation(value = "Update Animal Rest Service",response=Integer.class)
    @RequestMapping(value="/animal/updateAnimal",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Integer updateAnimal(@Valid  @RequestBody Animal a);
	
	@ApiOperation(value = "Delete Animal Rest Service",response=Integer.class)
    @RequestMapping(value="/animal/deleteAnimal",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Integer deleteAnimal(@RequestParam("animalName") String animalName);
	
	@ApiOperation(value = "Add Room Rest Service",response=Long.class)
    @RequestMapping(value="/room/addRoom",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Long addRoom(@Valid  @RequestBody Room r);
	
	@ApiOperation(value = "Get Room By ID Rest Service",response=Room.class)
    @RequestMapping(value="/room/id/{roomId}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Room getRoomById(@PathVariable("roomId")Long roomId);
	
	@ApiOperation(value = "Get Room By Name Rest Service",response=Room.class)
    @RequestMapping(value="/room/name/{roomTitle}",method=RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Room getRoomByTitle(@PathVariable("roomTitle")String roomTitle);
	
	@ApiOperation(value = "Update Room Rest Service",response=Integer.class)
    @RequestMapping(value="/room/updateRoom",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Integer updateRoom(@Valid  @RequestBody Room r);
	
	@ApiOperation(value = "Delete Room Rest Service",response=Integer.class)
    @RequestMapping(value="/room/deleteRoom",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody Integer deleteRoom(@RequestParam("roomName") String roomName);
	
}
