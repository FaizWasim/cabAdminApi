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
import com.booking.yoya.dao.VehicleNormalPriceDao;
import com.booking.yoya.dto.VehicleNormalPriceDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.model.VehicleNormalPrice;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class VehicleNormalPriceService {

	@Autowired
	private VehicleNormalPriceDao vehicleNormalPriceDao;

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

	public BaseMessageResponse<Object> createNewVehicleNormalPrice(VehicleNormalPrice vehicleNormalPrice) {

		VehicleNormalPrice savedVehicleNormalPrice = vehicleNormalPriceDao.save(vehicleNormalPrice);
		return BaseMessageResponse.builder().message("success").status(true).data(savedVehicleNormalPrice).build();
	}

	public BaseMessageResponse<Object> getAllVehicleNormalPrices() {

		List<VehicleNormalPrice> vehicleNormalPriceList = vehicleNormalPriceDao.findAll();
		if (vehicleNormalPriceList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(vehicleNormalPriceList).build();
		} else {

			List<VehicleNormalPriceDto> vehicleNormalPriceDtoList = vehicleNormalPriceList.stream()
					.map(new Function<VehicleNormalPrice, VehicleNormalPriceDto>() {
						@Override
						public VehicleNormalPriceDto apply(VehicleNormalPrice s) {
							VehicleNormalPriceDto sDto = new VehicleNormalPriceDto();

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

							sDto.setBaseFare(s.getBaseFare());
							sDto.setNormalHoursPricing(s.getNormalHoursPricing());
							sDto.setPeakHoursPricing(s.getPeakHoursPricing());
							sDto.setFreeWaitingTime(s.getFreeWaitingTime());
							sDto.setWaitingCharges(s.getWaitingCharges());
							sDto.setStoppageCharges(s.getStoppageCharges());
							sDto.setAdminCommision(s.getAdminCommision());
							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);
							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(vehicleNormalPriceDtoList)
					.build();

		}

	}

	public BaseMessageResponse<Object> deleteVehicleNormalPrice(Long vehicleNormalPriceId) {
		Optional<VehicleNormalPrice> vehicleNormalPrice = vehicleNormalPriceDao.findById(vehicleNormalPriceId);

		if (!vehicleNormalPrice.isPresent()) {
			return BaseMessageResponse.builder().message("vehicleNormalPrice not present").status(false)
					.data(vehicleNormalPrice).build();

		} else {
			vehicleNormalPriceDao.deleteById(vehicleNormalPrice.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(vehicleNormalPrice).build();
		}
	}

	public BaseMessageResponse<Object> updateVehicleNormalPrice(VehicleNormalPriceDto vehicleNormalPriceDto) {
		VehicleNormalPrice oldVehicleNormalPrice = vehicleNormalPriceDao.findById(vehicleNormalPriceDto.getId()).get();

		if (oldVehicleNormalPrice.getId() != null) {
			oldVehicleNormalPrice.setProcessId(vehicleNormalPriceDto.getProcessId());
			oldVehicleNormalPrice.setCountryId(vehicleNormalPriceDto.getCountryId());
			oldVehicleNormalPrice.setStateId(vehicleNormalPriceDto.getStateId());
			oldVehicleNormalPrice.setCityId(vehicleNormalPriceDto.getCityId());
			oldVehicleNormalPrice.setVehicleId(vehicleNormalPriceDto.getVehicleId());
			oldVehicleNormalPrice.setBaseFare(vehicleNormalPriceDto.getBaseFare());
			oldVehicleNormalPrice.setNormalHoursPricing(vehicleNormalPriceDto.getNormalHoursPricing());
			oldVehicleNormalPrice.setPeakHoursPricing(vehicleNormalPriceDto.getPeakHoursPricing());
			oldVehicleNormalPrice.setFreeWaitingTime(vehicleNormalPriceDto.getFreeWaitingTime());
			oldVehicleNormalPrice.setWaitingCharges(vehicleNormalPriceDto.getWaitingCharges());
			oldVehicleNormalPrice.setStoppageCharges(vehicleNormalPriceDto.getStoppageCharges());
			oldVehicleNormalPrice.setAdminCommision(vehicleNormalPriceDto.getAdminCommision());
			if (vehicleNormalPriceDto.getIsActive() == NumbersConstant.ONE.value)
				oldVehicleNormalPrice.setIsActive(true);
			else
				oldVehicleNormalPrice.setIsActive(false);
			VehicleNormalPrice savedVehicleNormalPrice = vehicleNormalPriceDao.save(oldVehicleNormalPrice);
			return BaseMessageResponse.builder().message("success").status(true).data(savedVehicleNormalPrice).build();
		} else {
			return BaseMessageResponse.builder().message("vehicleNormalPrice not present").status(false)
					.data(oldVehicleNormalPrice).build();
		}
	}

	public BaseMessageResponse<Object> vehicleNormalPriceById(Long vehicleNormalPriceId) {
		Optional<VehicleNormalPrice> vehicleNormalPrice = vehicleNormalPriceDao.findById(vehicleNormalPriceId);

		if (!vehicleNormalPrice.isPresent()) {
			return BaseMessageResponse.builder().message("vehicleNormalPrice not present").status(false)
					.data(vehicleNormalPrice).build();

		} else {
			VehicleNormalPriceDto sDto = new VehicleNormalPriceDto();

			Optional<Country> country = countryDao.findById(vehicleNormalPrice.get().getCountryId());
			Optional<State> state = stateDao.findById(vehicleNormalPrice.get().getStateId());
			Optional<City> city = cityDao.findById(vehicleNormalPrice.get().getCityId());
			Optional<Process> process = processDao.findById(vehicleNormalPrice.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(vehicleNormalPrice.get().getVehicleId());

			sDto.setId(vehicleNormalPrice.get().getId());
			sDto.setProcessId(vehicleNormalPrice.get().getProcessId());
			sDto.setCountryId(vehicleNormalPrice.get().getCountryId());
			sDto.setStateId(vehicleNormalPrice.get().getStateId());
			sDto.setCityId(vehicleNormalPrice.get().getCityId());
			sDto.setVehicleId(vehicleNormalPrice.get().getVehicleId());

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

			sDto.setBaseFare(vehicleNormalPrice.get().getBaseFare());
			sDto.setNormalHoursPricing(vehicleNormalPrice.get().getNormalHoursPricing());
			sDto.setPeakHoursPricing(vehicleNormalPrice.get().getPeakHoursPricing());
			sDto.setFreeWaitingTime(vehicleNormalPrice.get().getFreeWaitingTime());
			sDto.setWaitingCharges(vehicleNormalPrice.get().getWaitingCharges());
			sDto.setStoppageCharges(vehicleNormalPrice.get().getStoppageCharges());
			sDto.setAdminCommision(vehicleNormalPrice.get().getAdminCommision());
			if (vehicleNormalPrice.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
