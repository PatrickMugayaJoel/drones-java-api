package com.drones.api.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="drones")
public class Drone {
	
	@Id
	private String serialNumber;
	private String model;
	private int weightLimit;
	private int batteryCapacity;
	private String state;
	
	@OneToMany(mappedBy = "drone", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Medication> medications;
	
	public Drone() {}
	public Drone(String serialNumber, String model, int weightLimit, int batteryCapacity, String state) {
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(int weightLimit) {
		this.weightLimit = weightLimit;
	}
	public int getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@JsonManagedReference
	public Set<Medication> getMedications() {
		return medications;
	}
	public void setMedications(Set<Medication> medications) {
		this.medications = medications;
	}

    @Override
    public String toString()
    {
    	String medications = "";
    	
    	for(Medication m: this.medications) {
    		medications += m.toString() + ", ";
    	}
    	
        return "{serialNumber=" + this.serialNumber
                + ", model=" + this.model
                + ", weightLimit=" + this.weightLimit
                + ", batteryCapacity=" + this.batteryCapacity
                + ", state=" + this.state
                + ", state=" + this.state
                + ", medications=[" + medications + "]"
                + "}";
    }

}
