package com.arun.PetShop.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Appointment;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.AppointmentRepository;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.service.AppointmentService;
import com.arun.PetShop.utils.AppointmentStatus;

@Service 
public class AppointmentServiceimpl implements AppointmentService {

	@Autowired
	private  AppointmentRepository appointmentRepository;
	@Autowired
	private  UserRepository userRepository;

	@Override
	public Appointment createAppoitment(Appointment appointment, Long senderId, Long recipientId) {
		Optional<User> receipient = userRepository.findById(recipientId);
		Optional<User> sender = userRepository.findById(senderId);

		if (receipient.isPresent() && sender.isPresent()) {
			appointment.setPatient(sender.get());
			appointment.setVeterinarian(receipient.get());
			appointment.setAppointmentNo(null);
			appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
			return this.appointmentRepository.save(appointment);
		}
		throw new ResourceNotFoundException("Sender or Recipient not Found");

	}

	@Override
	public List<Appointment> getAllAppointment() {
		List<Appointment> all = this.appointmentRepository.findAll();
		return all;
	}

	@Override
	public Appointment updateAppointmnet(Long id, Appointment request) {

		Appointment exitAppointment = this.appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not Booked yet!"));
		if(!Objects.equals(exitAppointment.getStatus(), AppointmentStatus.WAITING_FOR_APPROVAL)) {
			throw new IllegalStateException("Sorry ,This appointment can't be updated !.");
		}

		/*
		 * exitAppointment.setCreatedAt(exitAppointment.getCreatedAt() != null ?
		 * exitAppointment.getCreatedAt() : request.getCreatedAt());
		 * exitAppointment.setDate(exitAppointment.getDate() != null ?
		 * exitAppointment.getDate() : request.getDate());
		 * exitAppointment.setPatient(exitAppointment.getPatient() != null ?
		 * exitAppointment.getPatient() : request.getPatient());
		 * exitAppointment.setStatus(exitAppointment.getStatus() != null ?
		 * exitAppointment.getStatus() : request.getStatus());
		 * exitAppointment.setReason(exitAppointment.getReason()!=null?exitAppointment.
		 * getReason():request.getReason());
		 * exitAppointment.setTime(exitAppointment.getTime()!=null?exitAppointment.
		 * getTime():request.getTime());
		 * exitAppointment.setVeterinarian(exitAppointment.getVeterinarian()!=null?
		 * exitAppointment.getVeterinarian():request.getVeterinarian());
		 */
		exitAppointment.setDate(exitAppointment.getDate() != null ?exitAppointment.getDate() : request.getDate());
		exitAppointment.setTime(exitAppointment.getTime() != null ?exitAppointment.getTime() : request.getTime());
		exitAppointment.setReason(exitAppointment.getReason() != null ?exitAppointment.getReason() : request.getReason());
		
		return this.appointmentRepository.save(exitAppointment);
	}

	@Override
	public void deleteAppointmnet(Long id) {
		Appointment appointment = this.appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not Booked yet!"));

		this.appointmentRepository.delete(appointment);

	}

	@Override
	public Appointment getAppointmentByid(Long id) {
		Appointment appointment = this.appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not Booked yet!"));
		return appointment;
	}

	@Override
	public Appointment getAppointmentByNo(String appointment) {
		Appointment appointmentByNo = this.appointmentRepository.findByAppointmentNo(appointment);
		return appointmentByNo;
	}

}
