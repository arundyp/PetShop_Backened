package com.arun.PetShop.service;

import org.springframework.data.domain.Page;

import com.arun.PetShop.model.Review;

public interface ReviewService {
	
	Review saveReview(Review review,Long reviewId,Long vetId);
	double getAverageRatingForVet(Long vetId);
	void updateReview(Long reviewId,Review review);
	Page<Review> findAllReviewByUserId(Long userId,int page,int size);
	void deleteReview(Long reviewerId);

}
