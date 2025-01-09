package com.arun.PetShop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arun.PetShop.model.User;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.response.ApiResponse;
import com.arun.PetShop.serviceImpl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping("/vet/create")
	public ResponseEntity<ApiResponse> create(@RequestBody RegistractionRequest request) {
		try {
			User registerUser = this.userService.registerUser(request);
			return ResponseEntity.ok(new ApiResponse("User Registered Sucessfully!", registerUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
		}

	}

	@GetMapping("/user/getSingle/{id}")
	public ResponseEntity<ApiResponse> getUserByID(@PathVariable Long id) {
		try {
			User singleUser = this.userService.getSingleUser(id);
			return ResponseEntity.ok(new ApiResponse("Sucess", singleUser));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/user/getAll")
	public ResponseEntity<ApiResponse> getAllUser() {
		try {
			List<User> allUsers = this.userService.getAllUsers();
			return ResponseEntity.ok(new ApiResponse("Sucess", allUsers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<ApiResponse> deleteSingleUser(@PathVariable Long id) {
		try {
			this.userService.deleteUser(id);
			return ResponseEntity.ok(new ApiResponse("Sucess", null));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<ApiResponse> updateUserDetails(@PathVariable Long id, @RequestBody User user) {
		try {
			User updateUser = this.userService.updateUser(id, user);
			return ResponseEntity.ok(new ApiResponse("Sucess", updateUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
		}
	}

}
