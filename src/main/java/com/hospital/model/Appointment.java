package com.hospital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull(message = "name shouldn't be null")
	private long doctorId;
	@NotNull(message = "name shouldn't be null")
	private long patientId;
	@NotNull(message = "name shouldn't be null")
    private String date;
	@NotNull(message = "name shouldn't be null")
    private String timeSlot;
	
    public Appointment() {
		super();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlotId(String timeSlot) {
		this.timeSlot = timeSlot;
	}
    
}
