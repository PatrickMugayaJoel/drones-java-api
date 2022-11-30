package com.drones.api.scheduler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.drones.api.models.Drone;
import com.drones.api.models.EventLog;
import com.drones.api.repository.DroneRepository;
import com.drones.api.repository.EventLogRepository;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private EventLogRepository eventLogRepository;
	
	@Scheduled(fixedDelay = 1000*60) // run every after a minute
	public void checkBatteryLevel() {
		List<Drone> drones = droneRepository.findAll();
		
		for(Drone drone: drones) {
			// periodically reducing the battery level of all drones
			if(drone.getBatteryCapacity() > 2) {
				drone.setBatteryCapacity(drone.getBatteryCapacity() - 2);
				droneRepository.save(drone);
			}
			
			// save drone state to the database
			EventLog eventLog = new EventLog(drone.toString());
			eventLogRepository.save(eventLog);
		}
	}
}
