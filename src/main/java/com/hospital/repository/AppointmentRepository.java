package com.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	public Optional<Appointment> findByDoctorIdAndDateAndTimeSlot(long doctorId,String date,String timeSlot);
	public List<Appointment> findByDoctorIdAndDate(long doctorId,String date);

}
