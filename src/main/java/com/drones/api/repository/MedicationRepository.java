package com.drones.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drones.api.models.Medication;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {
	Medication findById(int id);
}
