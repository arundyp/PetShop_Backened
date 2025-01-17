package com.arun.PetShop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Review;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.AppointmentRepository;
import com.arun.PetShop.repository.ReviewRepository;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.service.ReviewService;
import com.arun.PetShop.utils.AppointmentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final AppointmentRepository appointmentRepository;

	@Override
	public Review saveReview(Review review, Long reviewerId, Long veterinarianId) {
		 // 1. Check if the reviewer is same as the doctor being reviewed
        if(veterinarianId.equals(reviewerId)) {
            throw new IllegalArgumentException("Cant not Review");
        }
        //2. Check if the reviewer has previously submitted a review for this doctor.
         Optional<Review> existingReview = reviewRepository.findByVeterinarianIdAndPatientId(veterinarianId, reviewerId);
        if(existingReview.isPresent()) {
            throw new ResourceNotFoundException("Already exit exception");
        }
        //3.Check if the reviewer has gotten a completed appointment with this doctor.
		/*
		 * boolean hadCompletedAppointments =
		 * appointmentRepository.existsByVeterinarianIdAndPatientIdAndStatus(
		 * veterinarianId, reviewerId, AppointmentStatus.COMPLETED);
		 * if(!hadCompletedAppointments) { throw new
		 * IllegalStateException("Not Allowed"); }
		 */
        //4 Get the veterinarian  from the database
        User veterinarian = userRepository.findById(veterinarianId).orElseThrow(()->new ResourceNotFoundException("Not Found"));
        //4 Get the patient from the database
        User patient = userRepository.findById(reviewerId).orElseThrow(()->new ResourceNotFoundException("Not Found"));
        //5. Set both to the review
        review.setVeterinarian(veterinarian);
        review.setPatient(patient);
        //6. Save the review.
       return reviewRepository.save(review);
		
	}

	@Transactional
	@Override
	public double getAverageRatingForVet(Long vetId) {
		 List<Review> review = this.reviewRepository.findByVeterinarianId(vetId);
		 return review.isEmpty()? 0 :review.stream().mapToInt(Review :: getStars).average().orElse(0.0);
	}

	@Override
	public void updateReview(Long reviewId, Review review) {
		Review exitReview = this.reviewRepository.findById(reviewId).get();
		exitReview.setStars(review.getStars());
		exitReview.setFeedbak(review.getFeedbak());
		this.reviewRepository.save(exitReview);
		
	}

	@Override
	public Page<Review> findAllReviewByUserId(Long userId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return reviewRepository.FindAllByUsersId(userId, pageRequest);
	}

	@Override
	public void deleteReview(Long reviewerId) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 * @Override public void deleteReview(Long reviewerId) {
	 * reviewRepository.findById(reviewerId) .ifPresentOrElse(Review ::
	 * removeRelationShip, ()->{ throw new ResourceNotFoundException("Not Found");
	 * }); reviewRepository.deleteById(reviewerId); }
	 */
}
