package com.arun.PetShop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.arun.PetShop.model.Photo;
import com.arun.PetShop.response.ApiResponse;
import com.arun.PetShop.service.PhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PhotoController {

	private final PhotoService photoService;

	@PostMapping("/photo/upload")
	public ResponseEntity<ApiResponse> create(
			@RequestParam MultipartFile file,
			@RequestParam Long userId)
			throws SQLException, IOException {
		Photo savePhoto = this.photoService.savePhoto(file, userId);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully uploaded", savePhoto));
	}

	@GetMapping("/photo/{arun}")
	public ResponseEntity<ApiResponse> findByPhotoID(@PathVariable Long arun) {

		try {
			Optional<Photo> photoById = this.photoService.getPhotoById(arun);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully uploaded", photoById));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}

	}

	@PutMapping("/photo/update/{id}")
	public ResponseEntity<ApiResponse> updatePhoto(@PathVariable Long id, @RequestBody MultipartFile file) {
		try {
			Photo updatePhoto = this.photoService.updatePhoto(id, file);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Updated Successfully ", updatePhoto));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}

	}

	@GetMapping("/photo/getimage/{id}")
	public ResponseEntity<ApiResponse> getImage(@PathVariable Long id) {

		try {
			byte[] imageData = this.photoService.getImageData(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Sucess ", imageData));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}

	}

	@DeleteMapping("/photo/delete/{id}/user/{id1}")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long id,@PathVariable
			Long id1) {

		try {
			 this.photoService.deletePhoto(id, id1);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Deleted ", null));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}

	}

	
	
}
