package com.drones.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class DroneServiceRequest {
	
	@Pattern(regexp= "^[0-9a-zA-Z]{4,8}$", message="Should be a string of 4-8 non-special characters.")
	private String serialNumber;
	
	@Pattern(regexp= "^Lightweight|Middleweight|Cruiserweight|Heavyweight$",
			message="Invalid model! Valid values are 'Lightweight, Middleweight, Cruiserweight, Heavyweight'")
	private String model;
	
	@Min(0)
	@Max(500)
	private int weightLimit;
	
	@Min(0)
	@Max(100)
	private int batteryCapacity;
	
	@Pattern(regexp= "^IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING$",
			message="Invalid state! Valid values are 'IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING'")
	private String state;

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
}
