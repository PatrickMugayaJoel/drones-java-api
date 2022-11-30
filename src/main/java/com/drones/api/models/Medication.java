package com.drones.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="medications")
public class Medication {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int weight;
	private String code;
	private String image;
	
	@ManyToOne()
	@JoinColumn(name = "drone_serialNumber", nullable = false)
	private Drone drone;
	

	public Medication() {}
	public Medication(String name, int weight, String code, String image, Drone drone) {
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
		this.drone = drone;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@JsonBackReference
	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

    @Override
    public String toString()
    {
        return "{id=" + this.id
        	+ "name=" + this.name
            + ", weight=" + this.weight
            + ", code=" + this.code
            + ", image=" + this.image
            + "}";
    }

}
