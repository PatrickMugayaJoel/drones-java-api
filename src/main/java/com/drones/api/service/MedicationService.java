package com.drones.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drones.api.dto.MedicationServiceRequest;
import com.drones.api.exceptions.CustomException;
import com.drones.api.models.Drone;
import com.drones.api.models.Medication;
import com.drones.api.repository.MedicationRepository;


@Service
public class MedicationService {

	@Autowired
	private MedicationRepository repository;
	
	public Medication saveMedication(MedicationServiceRequest medicationRequest, Drone drone) throws CustomException {
		Medication medication = new Medication(medicationRequest.getName(), medicationRequest.getWeight(),
				medicationRequest.getCode(), medicationRequest.getImage(), drone);
		return repository.save(medication);
	}
}
