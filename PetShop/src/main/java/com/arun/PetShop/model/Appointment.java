package com.arun.PetShop.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;

import com.arun.PetShop.utils.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"patient","veterinarian"})
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reason;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime time;
	private String appointmentNo;
	@CreationTimestamp
	private LocalDate createdAt;

	@JoinColumn(name = "sender")
	@ManyToOne(fetch = FetchType.LAZY)
	private User patient;

	@JoinColumn(name = "recipient")
	@ManyToOne(fetch = FetchType.LAZY)
	private User veterinarian;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	public void addPatient(User sender) {
		this.setPatient(sender);
		if (sender.getAppointments() == null) {
			sender.setAppointments(new ArrayList<>());
		}
		sender.getAppointments().add(this);
	}
	
	public void addVeterinrian(User recipient) {
		this.setVeterinarian(recipient);
		if (recipient.getAppointments() == null) {
			recipient.setAppointments(new ArrayList<>());
		}
		recipient.getAppointments().add(this);
	}
	
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo=String.valueOf(String.valueOf(new Random().nextLong()).substring(1, 11));
	}
	
}
