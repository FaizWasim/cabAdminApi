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
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dao.VehicleOutStationPriceDao;
import com.booking.yoya.dto.VehicleOutstationPriceDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.model.VehicleOutstationPrice;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class VehicleOutStationPriceService {

	@Autowired
	private VehicleOutStationPriceDao vehicleOutstationPriceDao;

	@Autowired
	private VehicleDao vehicleDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private ProcessDao processDao;

	public BaseMessageResponse<Object> createNewVehicleOutstationPrice(VehicleOutstationPrice vehicleOutstationPrice) {

		VehicleOutstationPrice savedVehicleOutstationPrice = vehicleOutstationPriceDao.save(vehicleOutstationPrice);
		return BaseMessageResponse.builder().message("success").status(true).data(savedVehicleOutstationPrice).build();
	}

	public BaseMessageResponse<Object> getAllVehicleOutstationPrices() {

		List<VehicleOutstationPrice> vehicleOutstationPriceList = vehicleOutstationPriceDao.findAll();
		if (vehicleOutstationPriceList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(vehicleOutstationPriceList)
					.build();
		} else {
			List<VehicleOutstationPriceDto> vehicleOutstationPriceDtoList = vehicleOutstationPriceList.stream()
					.map(new Function<VehicleOutstationPrice, VehicleOutstationPriceDto>() {
						@Override
						public VehicleOutstationPriceDto apply(VehicleOutstationPrice s) {
							VehicleOutstationPriceDto sDto = new VehicleOutstationPriceDto();

							Optional<Country> country = countryDao.findById(s.getCountryId());
							Optional<State> state = stateDao.findById(s.getStateId());
							Optional<City> city = cityDao.findById(s.getCityId());
							Optional<Process> process = processDao.findById(s.getProcessId());
							Optional<Vehicle> vehicle = vehicleDao.findById(s.getVehicleId());

							sDto.setId(s.getId());
							sDto.setProcessId(s.getProcessId());
							sDto.setCountryId(s.getCountryId());
							sDto.setStateId(s.getStateId());
							sDto.setCityId(s.getCityId());
							sDto.setVehicleId(s.getVehicleId());

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

							if (!process.isPresent())
								sDto.setProcessName(StringsConstant.EMPTY.value);
							else
								sDto.setProcessName(process.get().getName());

							if (!vehicle.isPresent())
								sDto.setVehicleName(StringsConstant.EMPTY.value);
							else
								sDto.setVehicleName(vehicle.get().getName());

							sDto.setOneWayPerKmPrice(s.getOneWayPerKmPrice());
							sDto.setReturnWayPerKmPrice(s.getReturnWayPerKmPrice());
							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);
							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(vehicleOutstationPriceDtoList)
					.build();

		}

	}

	public BaseMessageResponse<Object> deleteVehicleOutstationPrice(Long vehicleOutstationPriceId) {
		Optional<VehicleOutstationPrice> vehicleOutstationPrice = vehicleOutstationPriceDao
				.findById(vehicleOutstationPriceId);

		if (!vehicleOutstationPrice.isPresent()) {
			return BaseMessageResponse.builder().message("vehicleOutstationPrice not present").status(false)
					.data(vehicleOutstationPrice).build();

		} else {
			vehicleOutstationPriceDao.deleteById(vehicleOutstationPrice.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(vehicleOutstationPrice).build();
		}
	}

	public BaseMessageResponse<Object> updateVehicleOutstationPrice(
			VehicleOutstationPriceDto vehicleOutstationPriceDto) {
		VehicleOutstationPrice oldVehicleOutstationPrice = vehicleOutstationPriceDao
				.findById(vehicleOutstationPriceDto.getId()).get();

		if (oldVehicleOutstationPrice.getId() != null) {
			oldVehicleOutstationPrice.setProcessId(vehicleOutstationPriceDto.getProcessId());
			oldVehicleOutstationPrice.setCountryId(vehicleOutstationPriceDto.getCountryId());
			oldVehicleOutstationPrice.setStateId(vehicleOutstationPriceDto.getStateId());
			oldVehicleOutstationPrice.setCityId(vehicleOutstationPriceDto.getCityId());
			oldVehicleOutstationPrice.setVehicleId(vehicleOutstationPriceDto.getVehicleId());
			oldVehicleOutstationPrice.setOneWayPerKmPrice(vehicleOutstationPriceDto.getOneWayPerKmPrice());
			oldVehicleOutstationPrice.setReturnWayPerKmPrice(vehicleOutstationPriceDto.getReturnWayPerKmPrice());

			VehicleOutstationPrice savedVehicleOutstationPrice = vehicleOutstationPriceDao
					.save(oldVehicleOutstationPrice);
			return BaseMessageResponse.builder().message("success").status(true).data(savedVehicleOutstationPrice)
					.build();
		} else {
			return BaseMessageResponse.builder().message("vehicleOutstationPrice not present").status(false)
					.data(oldVehicleOutstationPrice).build();
		}
	}

	public BaseMessageResponse<Object> vehicleOutstationPriceById(Long vehicleOutstationPriceId) {
		Optional<VehicleOutstationPrice> vehicleOutstationPrice = vehicleOutstationPriceDao
				.findById(vehicleOutstationPriceId);

		if (!vehicleOutstationPrice.isPresent()) {
			return BaseMessageResponse.builder().message("vehicleOutstationPrice not present").status(false)
					.data(vehicleOutstationPrice).build();

		} else {
			VehicleOutstationPriceDto sDto = new VehicleOutstationPriceDto();

			Optional<Country> country = countryDao.findById(vehicleOutstationPrice.get().getCountryId());
			Optional<State> state = stateDao.findById(vehicleOutstationPrice.get().getStateId());
			Optional<City> city = cityDao.findById(vehicleOutstationPrice.get().getCityId());
			Optional<Process> process = processDao.findById(vehicleOutstationPrice.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(vehicleOutstationPrice.get().getVehicleId());

			sDto.setId(vehicleOutstationPrice.get().getId());
			sDto.setProcessId(vehicleOutstationPrice.get().getProcessId());
			sDto.setCountryId(vehicleOutstationPrice.get().getCountryId());
			sDto.setStateId(vehicleOutstationPrice.get().getStateId());
			sDto.setCityId(vehicleOutstationPrice.get().getCityId());
			sDto.setVehicleId(vehicleOutstationPrice.get().getVehicleId());

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

			if (!process.isPresent())
				sDto.setProcessName(StringsConstant.EMPTY.value);
			else
				sDto.setProcessName(process.get().getName());

			if (!vehicle.isPresent())
				sDto.setVehicleName(StringsConstant.EMPTY.value);
			else
				sDto.setVehicleName(vehicle.get().getName());

			sDto.setOneWayPerKmPrice(vehicleOutstationPrice.get().getOneWayPerKmPrice());
			sDto.setReturnWayPerKmPrice(vehicleOutstationPrice.get().getReturnWayPerKmPrice());
			if (vehicleOutstationPrice.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
