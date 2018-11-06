package org.zoo.data.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="Animal_Favorite_Room")
public class AnimalFavoriteRoom {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Assigned_Room_ID",nullable=false)
	private Long assignedRoomId;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Animal.class)
	@JoinColumn(name="Animal_ID",nullable=false,foreignKey= @ForeignKey(name = "FK_FAVROOM_ANIMAL"),unique=false)
	private Animal animal;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Room.class)
	@JoinColumn(name="Room_ID",nullable=false,foreignKey= @ForeignKey(name = "FK_FAVROOM_ROOM"),unique=false)
	private Room room;
    
	public Long getAssignedRoomId() {
		return assignedRoomId;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public Room getRoom() {
		return room;
	}


	public AnimalFavoriteRoom(Long assignedRoomId, Animal animal, Room room) {
		super();
		this.assignedRoomId = assignedRoomId;
		this.animal = animal;
		this.room = room;
	}

	public AnimalFavoriteRoom() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
