package com.arun.PetShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.PetShop.model.Appointment;
import com.arun.PetShop.response.ApiResponse;
import com.arun.PetShop.service.AppointmentService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
public class AppointmentController {
	
	private final AppointmentService appointmentService;
	
	@PostMapping("/App/doAppintment")
	public ResponseEntity<ApiResponse> doAppointment(
			@RequestBody Appointment appointment,
			@RequestParam Long sender,
			@RequestParam Long receipient) {
		try {
			Appointment appoitment = this.appointmentService.createAppoitment(appointment, sender, receipient);
			return ResponseEntity.ok(new ApiResponse("Appointment Booked Sucessfully.!",appoitment));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/App/update/{id}")
	public ResponseEntity<ApiResponse> updateAppointment(@PathVariable Long id,@RequestBody Appointment appointment){
		
		try {
			Appointment updateAppointmnet = this.appointmentService.updateAppointmnet(id, appointment);
			return  ResponseEntity.ok(new ApiResponse("Updated sucessfully!.",updateAppointmnet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/App/delete/{id}")
	public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id){
		
		try {
			this.appointmentService.deleteAppointmnet(id);
			return  ResponseEntity.ok(new ApiResponse("Appointment Deleted sucessfully!.",null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	@GetMapping("/App/getSingle/{id}")
	public ResponseEntity<ApiResponse> getSingleAppointmnet(@PathVariable Long id){
		
		try {
			Appointment appointmentByid = this.appointmentService.getAppointmentByid(id);
			return  ResponseEntity.ok(new ApiResponse("Appointment fetched sucessfully!.",appointmentByid));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
