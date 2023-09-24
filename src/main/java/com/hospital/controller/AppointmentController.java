package com.hospital.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exception.DoctorTimeSlotNotAvailableException;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.TimeSlot;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.TimeSlotRepository;
import com.hospital.response.DoctorTimeSlotResponse;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/list-available-timeslots")
	public ResponseEntity<DoctorTimeSlotResponse> listAvailableSlots(@RequestParam long doctorId, @RequestParam String date){
		
		List<Appointment> AppointmentsList = appointmentRepository.findByDoctorIdAndDate(doctorId, date);
		
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		Doctor doctor = optionalDoctor.get();
		
		DoctorTimeSlotResponse doctorTimeSlotResponse = new DoctorTimeSlotResponse();
		doctorTimeSlotResponse.setDoctorName(doctor.getDoctorName());
		doctorTimeSlotResponse.setSpecilization(doctor.getSpecialization());
		
		List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
		List<String> timeslots = timeSlotList.stream().map(TimeSlot::getTimeSlot).collect(Collectors.toList());

		if(AppointmentsList.isEmpty()) {
			doctorTimeSlotResponse.setTimeSlots(timeslots);
			return new ResponseEntity<>(doctorTimeSlotResponse, HttpStatus.OK);
		}
		
		List<String> doctorBookedTimeslots = AppointmentsList.stream().map(Appointment::getTimeSlot).collect(Collectors.toList());
		timeslots.removeAll(doctorBookedTimeslots);
		
		if(timeslots.isEmpty()) {
			throw new DoctorTimeSlotNotAvailableException("Doctor Time slot not available for given Date.");
		}
		doctorTimeSlotResponse.setTimeSlots(timeslots);
		return new ResponseEntity<>(doctorTimeSlotResponse, HttpStatus.OK);
	}
	
	@PostMapping("/book-appointment")
	public ResponseEntity<HttpStatus> bookAppointment(@RequestBody @Valid Appointment appointment){
		Optional<Appointment> optionalAppointment = appointmentRepository
				.findByDoctorIdAndDateAndTimeSlot(appointment.getDoctorId(), appointment.getDate(), appointment.getTimeSlot());
		if(optionalAppointment.isPresent()) {
			throw new DoctorTimeSlotNotAvailableException("Doctor Time slot not available for given Date and Time.");
		}
		appointmentRepository.save(appointment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
