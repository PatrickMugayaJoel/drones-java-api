package com.drones.api.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table
public class EventLog {	
	@Id
	@GeneratedValue
	private int id;

	@CreationTimestamp
	@Column(name="timestamp", nullable = false, updatable = false)
	private Timestamp timestamp;
	private String log;
	
	public EventLog(String log) {
		this.log = log;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}

