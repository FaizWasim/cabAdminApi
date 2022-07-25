package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.ProcessDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dto.ProcessDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class ProcessService {

	@Autowired
	private ProcessDao processDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CityDao cityDao;

	public BaseMessageResponse<Object> createNewProcess(Process process) {
		Optional<Process> processName = processDao.findByProcessName(process.getName());

		if (processName.isPresent()) {
			return BaseMessageResponse.builder().message("process already present").status(true).data(processName)
					.build();

		} else {
			Process savedProcess = processDao.save(process);
			return BaseMessageResponse.builder().message("success").status(true).data(savedProcess).build();
		}
	}

	public BaseMessageResponse<Object> getAllProcesss() {

		List<Process> processList = processDao.findAll();
		if (processList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(processList).build();
		} else {
			List<ProcessDto> processDtoList = processList.stream().map(new Function<Process, ProcessDto>() {
				@Override
				public ProcessDto apply(Process s) {
					ProcessDto sDto = new ProcessDto();
					Optional<Country> country = countryDao.findById(s.getCountryId());
					Optional<State> state = stateDao.findById(s.getStateId());
					Optional<City> city = cityDao.findById(s.getCityId());

					sDto.setId(s.getId());
					sDto.setCountryId(s.getCountryId());
					sDto.setStateId(s.getStateId());
					sDto.setCityId(s.getCityId());
					if (!country.isPresent())
						sDto.setCountryName(StringsConstant.EMPTY.value);
					else
						sDto.setCountryName(country.get().getCountryName());

					if (!state.isPresent())
						sDto.setStateName(StringsConstant.EMPTY.value);
					else
						sDto.setStateName(state.get().getStateName());

					if (!city.isPresent())
						sDto.setCityName(StringsConstant.EMPTY.value);
					else
						sDto.setCityName(city.get().getCityName());

					sDto.setName(s.getName());
					sDto.setIcon(s.getIcon());
					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(processDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteProcess(Long processId) {
		Optional<Process> process = processDao.findById(processId);

		if (!process.isPresent()) {
			return BaseMessageResponse.builder().message("process not present").status(false).data(process).build();

		} else {
			processDao.deleteById(process.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(process).build();
		}
	}

	public BaseMessageResponse<Object> updateProcess(ProcessDto process) {
		Process oldProcess = processDao.findById(process.getId()).get();

		if (process.getId() != null) {
			oldProcess.setCountryId(process.getCountryId());
			oldProcess.setStateId(process.getStateId());
			oldProcess.setCityId(process.getCityId());
			oldProcess.setName(process.getName());
			oldProcess.setIcon(process.getIcon());
			if (process.getIsActive() == NumbersConstant.ONE.value)
				oldProcess.setIsActive(true);
			else
				oldProcess.setIsActive(false);

			Process savedProcess = processDao.save(oldProcess);
			return BaseMessageResponse.builder().message("success").status(true).data(savedProcess).build();
		} else {
			return BaseMessageResponse.builder().message("process not present").status(false).data(process).build();
		}
	}

	public BaseMessageResponse<Object> processById(Long processId) {
		Optional<Process> process = processDao.findById(processId);

		if (!process.isPresent()) {
			return BaseMessageResponse.builder().message("process not present").status(false).data(process).build();

		} else {
			ProcessDto sDto = new ProcessDto();
			Optional<Country> country = countryDao.findById(process.get().getCountryId());
			Optional<State> state = stateDao.findById(process.get().getStateId());
			Optional<City> city = cityDao.findById(process.get().getCityId());

			sDto.setId(process.get().getId());
			sDto.setCountryId(process.get().getCountryId());
			sDto.setStateId(process.get().getStateId());
			sDto.setCityId(process.get().getCityId());

			if (!country.isPresent())
				sDto.setCountryName(StringsConstant.EMPTY.value);
			else
				sDto.setCountryName(country.get().getCountryName());

			if (!state.isPresent())
				sDto.setStateName(StringsConstant.EMPTY.value);
			else
				sDto.setStateName(state.get().getStateName());

			if (!city.isPresent())
				sDto.setCityName(StringsConstant.EMPTY.value);
			else
				sDto.setCityName(city.get().getCityName());

			sDto.setName(process.get().getName());
			sDto.setIcon(process.get().getIcon());

			if (process.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
