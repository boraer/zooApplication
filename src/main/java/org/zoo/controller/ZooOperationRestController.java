package org.zoo.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zoo.data.entity.Animal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Zoo Operation Rest API",description="Operation API for Assigning Animal to Room",basePath="/operation")
@RequestMapping(value="/operation")
public interface ZooOperationRestController {

	@ApiOperation(value = "Add Animal To Room",response=Integer.class)
	@RequestMapping(value= "/room/{roomName}/addAnimalToRoom",method=RequestMethod.POST)
    @ResponseBody Integer addAnimalToRoom(@PathVariable("roomName")String roomName,@Valid @RequestBody Animal a);
	
	@ApiOperation(value = "Remove Animal From Room",response=Integer.class)
	@RequestMapping(value="/room/{roomName}/removeAnimalFromRoom",method=RequestMethod.POST)
    @ResponseBody Integer removeAnimalFromRoom(@PathVariable("roomName")String roomName,@Valid @RequestBody Animal a) ;
	
	@ApiOperation(value = "Transfer Animal Between Room",response=Integer.class)
	@RequestMapping(value="/room/transferAnimal",method=RequestMethod.POST)
    @ResponseBody Integer transferAnimalBetweenRoom(@RequestParam("oldRoomName")String oldRoomName,@RequestParam("newRoomName")String newRoomName,@Valid @RequestBody Animal a);

	@ApiOperation(value = "Add Room As Favorite To Animal ",response=Integer.class)
	@RequestMapping(value="/animal/addFavoriteRoom",method=RequestMethod.POST)
    @ResponseBody  Integer addFavRoomToAnimal(@RequestParam("animalName")String animalName,@RequestParam("roomName")String roomName);
	
	@ApiOperation(value = "Remove Room As Favorite From Animal ",response=Integer.class)
	@RequestMapping(value="/animal/removeFavoriteRoom",method=RequestMethod.POST)
    @ResponseBody  Integer removeFavRoomFromAnimal(@RequestParam("animalName")String animalName,@RequestParam("roomName")String roomName);
}
