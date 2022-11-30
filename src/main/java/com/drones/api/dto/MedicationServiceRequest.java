package com.drones.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class MedicationServiceRequest {

	@Pattern(regexp= "^[-0-9a-zA-Z_]*$", message="Medication name accepts only letters, numbers, ‘-‘ and ‘_’.")
	private String name;
	
	@Min(0)
	@Max(1000)
	private int weight;
	
	@Pattern(regexp= "^[0-9A-Z_]*$", message="Medication code accepts only upper case letters, numbers and underscore.")
	private String code;
	private String image;
	
	
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
	
	
}
