package com.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	List<Doctor> findBySpecialization(String specialization);
}
