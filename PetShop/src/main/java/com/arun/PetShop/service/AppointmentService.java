package com.arun.PetShop.service;

import java.util.List;

import com.arun.PetShop.model.Appointment;

public interface AppointmentService {
	Appointment createAppoitment(Appointment appointment,Long sender,Long recipient);
	List<Appointment>getAllAppointment();
	Appointment updateAppointmnet(Long id, Appointment request);
	void deleteAppointmnet(Long id);
	Appointment getAppointmentByid(Long id);
	Appointment getAppointmentByNo(String appointment);
}
