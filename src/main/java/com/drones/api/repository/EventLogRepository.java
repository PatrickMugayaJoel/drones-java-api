package com.drones.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drones.api.models.EventLog;

public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
	EventLog  findById(int id);
}

