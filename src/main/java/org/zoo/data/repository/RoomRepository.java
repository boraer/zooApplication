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
public interface RoomRepository extends CrudRepository<Room, java.io.Serializable> {

	 Optional<Room> findByRoomTitle(String roomtitle);
    
	 @Query("SELECT R.roomTitle FROM Room R,AnimalFavoriteRoom F WHERE R = F AND F.animal=  :#{#animal}")
	 List<String> findFavoriteRoomsForAnimal(@Param("animal")Animal animal);
}
