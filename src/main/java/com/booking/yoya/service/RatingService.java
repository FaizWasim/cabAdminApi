package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.dao.RatingDao;
import com.booking.yoya.dto.RatingDto;
import com.booking.yoya.model.Rating;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class RatingService {

	@Autowired
	private RatingDao ratingDao;

	public BaseMessageResponse<Object> createNewRating(Rating rating) {
		Rating savedRating = ratingDao.save(rating);
		return BaseMessageResponse.builder().message("success").status(true).data(savedRating).build();
	}

	public BaseMessageResponse<Object> getAllRatings() {

		List<Rating> ratingList = ratingDao.findAll();
		if (ratingList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(ratingList).build();
		} else {
			List<RatingDto> ratingDtoList = ratingList.stream().map(new Function<Rating, RatingDto>() {
				@Override
				public RatingDto apply(Rating s) {
					RatingDto sDto = new RatingDto();

					sDto.setId(s.getId());
					sDto.setBookingId(s.getBookingId());
					sDto.setUserId(s.getUserId());

					sDto.setDriverId(s.getDriverId());
					sDto.setRatingStars(s.getRatingStars());
					sDto.setRatingText(s.getRatingText());
					sDto.setRatedBy(s.getRatedBy());

					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(ratingDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteRating(Long ratingId) {
		Optional<Rating> rating = ratingDao.findById(ratingId);

		if (!rating.isPresent()) {
			return BaseMessageResponse.builder().message("rating not present").status(false).data(rating).build();

		} else {
			ratingDao.deleteById(rating.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(rating).build();
		}
	}

	public BaseMessageResponse<Object> updateRating(RatingDto rating) {
		Rating oldRating = ratingDao.findById(rating.getId()).get();

		if (rating.getId() != null) {
			oldRating.setBookingId(rating.getBookingId());
			oldRating.setUserId(rating.getUserId());

			oldRating.setDriverId(rating.getDriverId());
			oldRating.setRatingStars(rating.getRatingStars());
			oldRating.setRatingText(rating.getRatingText());
			oldRating.setRatedBy(rating.getRatedBy());
			if (rating.getIsActive() == NumbersConstant.ONE.value)
				oldRating.setIsActive(true);
			else
				oldRating.setIsActive(false);
			Rating savedRating = ratingDao.save(oldRating);
			return BaseMessageResponse.builder().message("success").status(true).data(savedRating).build();
		} else {
			return BaseMessageResponse.builder().message("rating not present").status(false).data(rating).build();
		}
	}

	public BaseMessageResponse<Object> ratingById(Long ratingId) {
		Optional<Rating> rating = ratingDao.findById(ratingId);

		if (!rating.isPresent()) {
			return BaseMessageResponse.builder().message("rating not present").status(false).data(rating).build();

		} else {
			RatingDto sDto = new RatingDto();
			sDto.setId(rating.get().getId());
			sDto.setBookingId(rating.get().getBookingId());
			sDto.setUserId(rating.get().getUserId());

			sDto.setDriverId(rating.get().getDriverId());
			sDto.setRatingStars(rating.get().getRatingStars());
			sDto.setRatingText(rating.get().getRatingText());
			sDto.setRatedBy(rating.get().getRatedBy());
			if (rating.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
