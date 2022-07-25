package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dto.StateDto;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.State;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class StateService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CountryDao countryDao;

	public BaseMessageResponse<Object> createNewState(State state) {
		Optional<State> stateName = stateDao.findByStateName(state.getStateName());

		if (stateName.isPresent()) {
			return BaseMessageResponse.builder().message("state already present").status(true).data(stateName).build();

		} else {
			State savedState = stateDao.save(state);
			return BaseMessageResponse.builder().message("success").status(true).data(savedState).build();
		}
	}

	public BaseMessageResponse<Object> getAllStates() {

		try {

			List<State> stateList = stateDao.findAll();
			if (stateList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(stateList).build();
			} else {
				List<StateDto> stateDtoList = stateList.stream().map(new Function<State, StateDto>() {
					@Override
					public StateDto apply(State s) {
						Optional<Country> country = countryDao.findById(s.getCountryId());
						StateDto sDto = new StateDto();

						sDto.setId(s.getId());
						sDto.setCountryId(s.getCountryId());
						sDto.setStateName(s.getStateName());
						if (!country.isPresent())
							sDto.setCountryName(StringsConstant.EMPTY.value);
						else
							sDto.setCountryName(country.get().getCountryName());

						if (s.getIsActive().equals(true))
							sDto.setIsActive(NumbersConstant.ONE.value);
						else
							sDto.setIsActive(NumbersConstant.ZERO.value);

						return sDto;
					}
				}).collect(Collectors.toList());

				return BaseMessageResponse.builder().message("success").status(true).data(stateDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();

	}

	public BaseMessageResponse<Object> deleteState(Long stateId) {
		Optional<State> state = stateDao.findById(stateId);

		if (!state.isPresent()) {
			return BaseMessageResponse.builder().message("state not present").status(false).data(state).build();

		} else {
			stateDao.deleteById(state.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(state).build();
		}
	}

	public BaseMessageResponse<Object> updateState(StateDto state) {
		State oldState = stateDao.findById(state.getId()).get();

		if (state.getId() != null) {
			oldState.setCountryId(state.getCountryId());
			oldState.setStateName(state.getStateName());
			if (state.getIsActive() == NumbersConstant.ONE.value)
				oldState.setIsActive(true);
			else
				oldState.setIsActive(false);
			State savedState = stateDao.save(oldState);
			return BaseMessageResponse.builder().message("success").status(true).data(savedState).build();
		} else {
			return BaseMessageResponse.builder().message("state not present").status(false).data(state).build();
		}
	}

	public BaseMessageResponse<Object> stateById(Long stateId) {
		try {
			Optional<State> state = stateDao.findById(stateId);

			if (!state.isPresent()) {
				return BaseMessageResponse.builder().message("state not present").status(false).data(state).build();

			} else {
				StateDto sDto = new StateDto();
				sDto.setId(state.get().getId());
				sDto.setCountryId(state.get().getCountryId());
				sDto.setStateName(state.get().getStateName());
				Optional<Country> country = countryDao.findById(state.get().getCountryId());
				if (!country.isPresent())
					sDto.setCountryName(StringsConstant.EMPTY.value);
				else
					sDto.setCountryName(country.get().getCountryName());

				if (state.get().getIsActive().equals(true))
					sDto.setIsActive(NumbersConstant.ONE.value);
				else
					sDto.setIsActive(NumbersConstant.ZERO.value);
				return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();
	}

	public BaseMessageResponse<Object> findAllStatesByCountryId(Long countryId) {
		try {
			List<State> stateList = stateDao.findByCountryId(countryId);

			if (stateList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(stateList).build();
			} else {
				List<StateDto> stateDtoList = stateList.stream().map(new Function<State, StateDto>() {
					@Override
					public StateDto apply(State s) {
						Optional<Country> country = countryDao.findById(s.getCountryId());
						StateDto sDto = new StateDto();

						sDto.setId(s.getId());
						sDto.setCountryId(s.getCountryId());
						sDto.setStateName(s.getStateName());
						if (!country.isPresent())
							sDto.setCountryName(StringsConstant.EMPTY.value);
						else
							sDto.setCountryName(country.get().getCountryName());

						if (s.getIsActive().equals(true))
							sDto.setIsActive(NumbersConstant.ONE.value);
						else
							sDto.setIsActive(NumbersConstant.ZERO.value);

						return sDto;
					}
				}).collect(Collectors.toList());

				return BaseMessageResponse.builder().message("success").status(true).data(stateDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();
	}

}
