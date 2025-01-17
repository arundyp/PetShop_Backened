package com.arun.PetShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arun.PetShop.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	@Query("SELECT r FROM Review r WHERE r.patient.id =:userId OR r.veterinarian.id =:userId ")
	Page<Review> FindAllByUsersId(@Param("userId") Long userId,PageRequest pageRequest);
	
	List<Review> findByVeterinarianId(Long vetId);

	Optional<Review> findByVeterinarianIdAndPatientId(Long veterinarianId, Long reviewerId);

}
