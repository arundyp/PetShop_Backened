package com.arun.PetShop.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Review;
import com.arun.PetShop.response.ApiResponse;
import com.arun.PetShop.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;
	
	
	@PostMapping("/review/save")
    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review review,
                                                 @RequestParam Long reviewerId,
                                                 @RequestParam Long vetId) {
        try {
          Review savedReview =  reviewService.saveReview(review, reviewerId, vetId);
            return ResponseEntity.ok(new ApiResponse("sucessfully Created", savedReview.getId()));
        } catch (IllegalArgumentException | IllegalStateException e) {
           return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ApiResponse(e.getMessage(), null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/review/update/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(@RequestBody Review updateRequest,
                                                    @PathVariable Long reviewId){        try {
            reviewService.updateReview(reviewId, updateRequest);
            return ResponseEntity.ok(new ApiResponse("Sucessfully updated", null));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

	/*
	 * @DeleteMapping(UrlMapping.DELETE_REVIEW) public ResponseEntity<ApiResponse>
	 * deleteReview(@PathVariable Long reviewId){ try {
	 * reviewService.deleteReview(reviewId); return ResponseEntity.ok(new
	 * ApiResponse(FeedBackMessage.DELETE_SUCCESS, null)); } catch
	 * (ResourceNotFoundException e) { return
	 * ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
	 * } }
	 */

    @GetMapping("/review/getReview/{userId}")
    public ResponseEntity<ApiResponse> getReviewsByUserID(@PathVariable Long userId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size) {
        Page<Review> reviewPage = reviewService.findAllReviewByUserId(userId, page, size);
      //  Page<ReviewDto> reviewDtos = reviewPage.map((element) -> modelMapper.map(element, ReviewDto.class));
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("Found review", reviewPage));
    }

    @GetMapping("/review/getAvgrating/{vetId}")
    public ResponseEntity<ApiResponse> getAverageRatingForVet(@PathVariable Long vetId){
        double averageRating = reviewService.getAverageRatingForVet(vetId);
        return ResponseEntity.ok(new ApiResponse("Rating found for doctor", averageRating));
    }

}
