package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
	
}
