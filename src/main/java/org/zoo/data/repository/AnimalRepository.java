package org.zoo.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zoo.data.entity.Animal;
import org.zoo.data.entity.Room;

import java.lang.String;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, java.io.Serializable> {

	Optional<Animal> findByAnimalName(String animalname);
	
	List<Animal> findAllByOrderByAnimalNameAsc();
	
	List<Animal> findAllByOrderByAnimalNameDesc();
	
	@Query(value="Select a from Animal a Order By a.createDate Asc")
	List<Animal> findAllAnimalByCreateAsc();

	@Query(value="Select * from Animal Order By Create_Date Desc  ",nativeQuery=true)
	List<Animal> findAllAnimalByCreateDesc();

	@Query("Select a from Animal a Where a.room= :#{#room} Order By a.createDate Asc")
	List<Animal>findAllAnimalInRoomByCreateAsc(@Param("room")Room r);
	
	@Query("Select a from Animal a Where a.room= :#{#room} Order By a.createDate Desc")
	List<Animal>findAllAnimalInRoomByCreateDesc(@Param("room")Room r);
	
}
