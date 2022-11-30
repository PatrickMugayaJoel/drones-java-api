package com.drones.api.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drones.api.dto.DroneServiceRequest;
import com.drones.api.dto.MedicationServiceRequest;
import com.drones.api.exceptions.CustomException;
import com.drones.api.models.Drone;
import com.drones.api.models.Medication;
import com.drones.api.repository.DroneRepository;
import com.drones.api.service.DroneService;
import com.drones.api.service.MedicationService;

@RestController
@RequestMapping("/drones")
public class AppController {
	
	@Autowired
	private DroneService droneService;

	@Autowired
	private	MedicationService medicationService;

	@Autowired
	private DroneRepository droneRepository;

	@PostMapping("/register")
	public ResponseEntity<Drone> saveDrone(@RequestBody @Valid DroneServiceRequest droneRequest){
		try {
			// Prevent Updating existing drone at registration
			return ResponseEntity.ok(droneService.getDroneBySerialNumber(droneRequest.getSerialNumber()));
		} catch (CustomException e) {
			return new ResponseEntity<>(droneService.saveDrone(droneRequest), HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/{serialNumber}")
	public ResponseEntity<Drone> getDroneBySerialNumber(@PathVariable String serialNumber) throws CustomException{
		return ResponseEntity.ok(droneService.getDroneBySerialNumber(serialNumber));
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Drone>> getAllIdleDrones(){
		return ResponseEntity.ok(droneService.getDronesAvailableForLoading("IDLE"));
	}
	
	@GetMapping(path = "/{serialNumber}/battery_level", produces = "application/json")
	public int getDroneBatteryLevel(@PathVariable String serialNumber) throws CustomException{
		return droneService.getDroneBySerialNumber(serialNumber).getBatteryCapacity();
	}

	@GetMapping("/{serialNumber}/load")
	public ResponseEntity<Set<Medication>> getDroneLoad(@PathVariable String serialNumber) throws CustomException{
		Drone drone = droneService.getDroneBySerialNumber(serialNumber);
		return new ResponseEntity<>(drone.getMedications(), HttpStatus.OK);
	}
	
	@PostMapping("/{serialNumber}/load")
	public ResponseEntity<Drone> loadDrone(@PathVariable String serialNumber, @RequestBody @Valid MedicationServiceRequest medicationRequest)
			throws CustomException{
		
		Drone drone = droneService.getDroneBySerialNumber(serialNumber);
		
		// Check if drone battery level is below 25
		if(drone.getBatteryCapacity() < 25) throw new CustomException("Drone's battery is too low to be loaded.");
		
		int currentLoadWeight = 0;
		for(Medication m: drone.getMedications()) {
			currentLoadWeight += m.getWeight();
		}

		// Check if drone weight limit is exceeded
		if((currentLoadWeight + medicationRequest.getWeight()) > drone.getWeightLimit()) {
			throw new CustomException("Drone's load weight limit has been Exceeded.");
		}
		
		medicationService.saveMedication(medicationRequest, drone);
		
		// Update drone state after loading
		if("IDLE".equals(drone.getState()) || "LOADING".equals(drone.getState())) {
			drone.setState("LOADED");
			droneRepository.save(drone);
		}
		drone = droneService.getDroneBySerialNumber(serialNumber);
		return ResponseEntity.ok(drone);
	}
	
}
