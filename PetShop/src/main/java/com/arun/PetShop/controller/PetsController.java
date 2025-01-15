package com.arun.PetShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Pets;
import com.arun.PetShop.response.ApiResponse;
import com.arun.PetShop.service.PetService;

@RestController
public class PetsController {
	
	@Autowired
	private  PetService service;

	
	
	@PostMapping("/pets/create")
	public ResponseEntity<ApiResponse> createPets(@RequestBody List<Pets> pet) {

		try {
			List<Pets> pets = this.service.savePetForAppointment(pet);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Pets Created Sucessfully.!", pets));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
		}

	}
	
	@PostMapping("/pets/update/{id}")
	public ResponseEntity<ApiResponse> updatePet(@PathVariable Long id ,@RequestBody Pets pet) {

		try {
			Pets pets = this.service.updatePets(id, pet);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Pets updated Sucessfully.!", pets));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
		}

	}
	
	
	@GetMapping("/pets/getPets/{id}")
	public ResponseEntity<ApiResponse> getSinglePet(@PathVariable Long id ) {

		try {
			Pets pets = this.service.getPetsByID(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Fetched Sucessfully.!", pets));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}

	}
	
	
	@DeleteMapping("/pets/delete/{id}")
	public ResponseEntity<ApiResponse> deletePets(@PathVariable Long id ) {

		try {
			 this.service.deletePets(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Deleted Sucessfully.!", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
