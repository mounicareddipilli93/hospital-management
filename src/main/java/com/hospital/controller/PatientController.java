package com.hospital.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exception.PatientDuplicateException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/add-patient")
	public ResponseEntity<HttpStatus> addPatient(@RequestBody @Valid Patient patient) {
		
		Optional<Patient> optionalPatient = patientRepository.findByMobile(patient.getMobile());	
		if(optionalPatient.isPresent()) {
			throw new PatientDuplicateException("Patient already exists with given mobile number");
		}
		patientRepository.save(patient);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update-patient/{mobile}")
	public ResponseEntity<HttpStatus> upadatePatient(@PathVariable String mobile, @RequestBody @Valid Patient patient){
		
		Optional<Patient> optionalPatient = patientRepository.findByMobile(mobile);	
		if(optionalPatient.isPresent()) {
			Patient patientToBeUpdated = optionalPatient.get();
			patientToBeUpdated.setName(patient.getName());
			patientToBeUpdated.setAge(patient.getAge());
			patientToBeUpdated.setGender(patient.getGender());
			patientToBeUpdated.setMobile(patient.getMobile());
			patientRepository.save(patientToBeUpdated);
			return new ResponseEntity<>(HttpStatus.OK);

		}
		throw new ResourceNotFoundException("Patient does not exist with given mobile number");
		
	}
	@DeleteMapping("/delete-patient/{id}")
	public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id){
		Patient patient = patientRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id : " + id));
		
		patientRepository.delete(patient);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
