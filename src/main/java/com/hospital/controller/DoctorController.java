package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/get-doctors")
	public ResponseEntity<List<Doctor>> getDoctors(){
		List<Doctor> doctorList = doctorRepository.findAll();
		
		if(doctorList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		 return new ResponseEntity<>(doctorList, HttpStatus.OK);
	}
	
	@GetMapping("/get-doctors/{specialization}")
	public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable("specialization") String specialization){
		List<Doctor> doctorList = doctorRepository.findBySpecialization(specialization);
		if(doctorList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(doctorList, HttpStatus.OK);
	}
}
