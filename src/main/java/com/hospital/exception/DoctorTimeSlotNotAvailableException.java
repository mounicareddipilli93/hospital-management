package com.hospital.exception;

public class DoctorTimeSlotNotAvailableException extends RuntimeException{
	 public DoctorTimeSlotNotAvailableException(String message) {
	        super(message);
	    }
}
