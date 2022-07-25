package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.dao.RideCancellationDao;
import com.booking.yoya.dto.RideCancellationDto;
import com.booking.yoya.model.RideCancellation;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class RideCancellationService {

	@Autowired
	private RideCancellationDao rideCancellationDao;

	public BaseMessageResponse<Object> createNewRideCancellation(RideCancellation rideCancellation) {

		RideCancellation savedRideCancellation = rideCancellationDao.save(rideCancellation);
		return BaseMessageResponse.builder().message("success").status(true).data(savedRideCancellation).build();
	}

	public BaseMessageResponse<Object> getAllRideCancellations() {

		List<RideCancellation> rideCancellationList = rideCancellationDao.findAll();
		if (rideCancellationList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(rideCancellationList).build();
		} else {
			List<RideCancellationDto> rideCancellationDtoList = rideCancellationList.stream()
					.map(new Function<RideCancellation, RideCancellationDto>() {
						@Override
						public RideCancellationDto apply(RideCancellation s) {
							RideCancellationDto sDto = new RideCancellationDto();

							sDto.setId(s.getId());
							sDto.setReasonFor(s.getReasonFor());
							sDto.setCancellation(s.getCancellation());
							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);

							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(rideCancellationDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteRideCancellation(Long rideCancellationId) {
		Optional<RideCancellation> rideCancellation = rideCancellationDao.findById(rideCancellationId);

		if (!rideCancellation.isPresent()) {
			return BaseMessageResponse.builder().message("rideCancellation not present").status(false)
					.data(rideCancellation).build();

		} else {
			rideCancellationDao.deleteById(rideCancellation.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(rideCancellation).build();
		}
	}

	public BaseMessageResponse<Object> updateRideCancellation(RideCancellationDto rideCancellation) {
		RideCancellation oldRideCancellation = rideCancellationDao.findById(rideCancellation.getId()).get();

		if (rideCancellation.getId() != null) {
			oldRideCancellation.setReasonFor(rideCancellation.getReasonFor());
			oldRideCancellation.setCancellation(rideCancellation.getCancellation());
			if (rideCancellation.getIsActive() == NumbersConstant.ONE.value)
				oldRideCancellation.setIsActive(true);
			else
				oldRideCancellation.setIsActive(false);

			RideCancellation savedRideCancellation = rideCancellationDao.save(oldRideCancellation);
			return BaseMessageResponse.builder().message("success").status(true).data(savedRideCancellation).build();
		} else {
			return BaseMessageResponse.builder().message("rideCancellation not present").status(false)
					.data(rideCancellation).build();
		}
	}

	public BaseMessageResponse<Object> rideCancellationById(Long rideCancellationId) {
		Optional<RideCancellation> rideCancellation = rideCancellationDao.findById(rideCancellationId);

		if (!rideCancellation.isPresent()) {
			return BaseMessageResponse.builder().message("rideCancellation not present").status(false)
					.data(rideCancellation).build();

		} else {
			RideCancellationDto sDto = new RideCancellationDto();
			sDto.setId(rideCancellation.get().getId());
			sDto.setReasonFor(rideCancellation.get().getReasonFor());
			sDto.setCancellation(rideCancellation.get().getCancellation());
			if (rideCancellation.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
