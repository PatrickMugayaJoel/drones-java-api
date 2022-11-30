package com.drones.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drones.api.models.Drone;

public interface DroneRepository extends JpaRepository<Drone,String>  {
	Drone findBySerialNumber(String serialNumber);
	List<Drone> findByState(String state);
}
