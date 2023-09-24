package com.hospital.response;

import java.util.List;

public class DoctorTimeSlotResponse {

	private String DoctorName;
	private String Specilization;
	List<String> timeSlots;
	
	public DoctorTimeSlotResponse() {

	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getSpecilization() {
		return Specilization;
	}
	public void setSpecilization(String specilization) {
		Specilization = specilization;
	}
	public List<String> getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(List<String> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	
}
