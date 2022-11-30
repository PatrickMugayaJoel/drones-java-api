package com.drones.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drones.api.dto.DroneServiceRequest;
import com.drones.api.exceptions.CustomException;
import com.drones.api.models.Drone;
import com.drones.api.repository.DroneRepository;

@Service
public class DroneService {

	@Autowired
	private DroneRepository repository;

	public List<Drone> getDronesAvailableForLoading(String state){
		return repository.findByState(state);
	}

	public Drone saveDrone(DroneServiceRequest droneRequest) {
		Drone drone = new Drone(droneRequest.getSerialNumber(), droneRequest.getModel(), droneRequest.getWeightLimit(),
				droneRequest.getBatteryCapacity(), droneRequest.getState());
		return repository.save(drone);
	}

	public Drone getDroneBySerialNumber(String serialNumber) throws CustomException{
		Drone drone = repository.findBySerialNumber(serialNumber);
		if(drone!=null) {
			return drone;
		}else {
			throw new CustomException("Drone with serial number: "+serialNumber+" was not found.");
		}
	}

}
