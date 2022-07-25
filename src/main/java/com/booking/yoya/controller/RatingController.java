package com.booking.yoya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.RatingDto;
import com.booking.yoya.model.Rating;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.RatingService;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {
	@Autowired
	private RatingService ratingService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createRating(@RequestBody Rating rating) {
		return new ServiceResponse<BaseMessageResponse>(ratingService.createNewRating(rating));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList() {
		return new ServiceResponse<BaseMessageResponse>(ratingService.getAllRatings());
	}

	@DeleteMapping(value = "{ratingId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteRating(@PathVariable Long ratingId) {
		return new ServiceResponse<BaseMessageResponse>(ratingService.deleteRating(ratingId));
	}

	@GetMapping(value = "{ratingId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> ratingById(@PathVariable Long ratingId) {
		return new ServiceResponse<BaseMessageResponse>(ratingService.ratingById(ratingId));
	}

	@PutMapping(value = "{ratingId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> updateRating(@PathVariable Long ratingId, @RequestBody RatingDto rating) {
		rating.setId(ratingId);
		return new ServiceResponse<BaseMessageResponse>(ratingService.updateRating(rating));
	}
}
