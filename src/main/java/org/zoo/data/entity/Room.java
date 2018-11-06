package org.zoo.data.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect
@Entity
@Table(name="Room")
public class Room {

	@ApiModelProperty
	//@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Room_ID",nullable=false)
	private Long roomId;
	
	@ApiModelProperty
	@NotNull
	@Size(min=1,max=50,message="Room Title is not valid")
	@Column(name="Room_Title",nullable=false)
	private String roomTitle;
	
	@ApiModelProperty
	@NotNull
	@Column(name="Room_Size",nullable=false)
	private Long size;
	
	@ApiModelProperty
	@NotNull
	@Column(name="Room_Capacity",nullable=false)
	private Long capacity;
	
	@ApiModelProperty
	@Column(name="Create_Date",nullable=false)
	private Timestamp createDate;

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,cascade= {CascadeType.ALL})
	@JoinColumn(nullable=true,name="Room_ID",referencedColumnName="Room_ID")
	private List<Animal> animalAssigns;
	
	public Long getRoomId() {
		return roomId;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	
	public List<Animal> getAnimalAssigns() {
		return animalAssigns;
	}

	public void setAnimalAssigns(List<Animal> animalAssigns) {
		this.animalAssigns = animalAssigns;
	}

	

	public Room(Long roomId, String roomTitle, Long size, Long capacity, Timestamp createDate,
			List<Animal> animalAssigns) {
		super();
		this.roomId = roomId;
		this.roomTitle = roomTitle;
		this.size = size;
		this.capacity = capacity;
		this.createDate = createDate;
		this.animalAssigns = animalAssigns;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result + ((roomTitle == null) ? 0 : roomTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (roomTitle == null) {
			if (other.roomTitle != null)
				return false;
		} else if (!roomTitle.equals(other.roomTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomTitle=" + roomTitle + ", size=" + size + ", capacity=" + capacity
				+ ", createDate=" + createDate + "]";
	}

	
	
	
}
