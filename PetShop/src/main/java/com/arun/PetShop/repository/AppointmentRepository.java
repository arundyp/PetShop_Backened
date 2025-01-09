package com.arun.PetShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.PetShop.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
	Appointment findByAppointmentNo(String AppNumber);
}
