package org.zoo.data.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect
@Entity
@Table(name="Animal")
public class Animal {

	@ApiModelProperty(readOnly=true)
	//@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Animal_ID",nullable=false)
	private Long animalId;
	
	@ApiModelProperty
	@NotNull
	@Size(min=1,max=50,message="Animal Name is not valid")
	@Column(name="Animal_Name",nullable=false,length=50,unique=true)
	private String animalName;
	
	@ApiModelProperty
	@Column(name="Create_Date",nullable=false)
	private Timestamp createDate;
	
	@ApiModelProperty
	@NotNull
	@Size(min=1,max=2,message="Type is not valid could be consist of 2 character")
	@Column(name="Happy_Type",nullable=false)
	private String type;
	
	@ApiModelProperty
	@NotNull
	@Column(name="Preference",nullable=false)
	private Long preference;

	@JsonIgnore
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="Room_ID",nullable=true,updatable=true,referencedColumnName="Room_ID",columnDefinition="BIGINT")
	private Room room;
	
	@ApiModelProperty
	@Column(name="Located_Date",nullable=true)
	private Timestamp locatedDate;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Animal_Favorite_Room",joinColumns=@JoinColumn(name="Animal_ID", referencedColumnName="Animal_ID",unique=false),
    inverseJoinColumns=@JoinColumn(referencedColumnName="Room_ID",name="Room_ID",unique=false))
    private List<Room> favRooms;
	
	
	public Long getAnimalId() {
		return animalId;
	}


	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPreference() {
		return preference;
	}

	public void setPreference(Long preference) {
		this.preference = preference;
	}
	


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Timestamp getLocatedDate() {
		return locatedDate;
	}


	public void setLocatedDate(Timestamp locatedDate) {
		this.locatedDate = locatedDate;
	}


	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public List<Room> getFavRooms() {
		return favRooms;
	}


	public void setFavRooms(List<Room> favRooms) {
		this.favRooms = favRooms;
	}


	public Animal(Long animalId, String animalName, Timestamp createDate, String type, Long preference,
			Room room, Timestamp locatedDate) {
		super();
		this.animalId = animalId;
		this.animalName = animalName;
		this.createDate = createDate;
		this.type = type;
		this.preference = preference;
		this.room = room;
		this.locatedDate = locatedDate;
	}


	public Animal(@NotNull @Size(min = 1, max = 50, message = "Animal Name is not valid") String animalName,
			@NotNull @Size(min = 1, max = 2, message = "Type is not valid could be consist of 2 character") String type,
			@NotNull Long preference) {
		super();
		this.animalName = animalName;
		this.type = type;
		this.preference = preference;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animalId == null) ? 0 : animalId.hashCode());
		result = prime * result + ((animalName == null) ? 0 : animalName.hashCode());
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
		Animal other = (Animal) obj;
		if (animalId == null) {
			if (other.animalId != null)
				return false;
		} else if (!animalId.equals(other.animalId))
			return false;
		if (animalName == null) {
			if (other.animalName != null)
				return false;
		} else if (!animalName.equals(other.animalName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Animal [animalId=" + animalId + ", animalName=" + animalName + ", createDate=" + createDate + ", type="
				+ type + ", preference=" + preference + ", locatedDate=" + locatedDate + "]";
	}
	
	
	
}
