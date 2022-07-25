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
import com.booking.yoya.dao.RentalPackagePriceDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dto.RentalPackagePriceDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.RentalPackagePrice;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class RentalPackagePriceService {

	@Autowired
	private RentalPackagePriceDao rentalPackagePriceDao;

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

	public BaseMessageResponse<Object> createNewRentalPackagePrice(RentalPackagePrice rentalPackagePrice) {

		RentalPackagePrice savedRentalPackagePrice = rentalPackagePriceDao.save(rentalPackagePrice);
		return BaseMessageResponse.builder().message("success").status(true).data(savedRentalPackagePrice).build();
	}

	public BaseMessageResponse<Object> getAllRentalPackagePrices() {

		List<RentalPackagePrice> rentalPackagePriceList = rentalPackagePriceDao.findAll();
		if (rentalPackagePriceList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(rentalPackagePriceList).build();
		} else {
			List<RentalPackagePriceDto> rentalPackagePriceDtoList = rentalPackagePriceList.stream()
					.map(new Function<RentalPackagePrice, RentalPackagePriceDto>() {
						@Override
						public RentalPackagePriceDto apply(RentalPackagePrice s) {
							RentalPackagePriceDto sDto = new RentalPackagePriceDto();

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

							sDto.setPackagePrice(s.getPackagePrice());
							sDto.setAdditionalKmPrice(s.getAdditionalKmPrice());
							sDto.setAdditionalMinPrice(s.getAdditionalMinPrice());

							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);

							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(rentalPackagePriceDtoList)
					.build();

		}

	}

	public BaseMessageResponse<Object> deleteRentalPackagePrice(Long rentalPackagePriceId) {
		Optional<RentalPackagePrice> rentalPackagePrice = rentalPackagePriceDao.findById(rentalPackagePriceId);

		if (!rentalPackagePrice.isPresent()) {
			return BaseMessageResponse.builder().message("rentalPackagePrice not present").status(false)
					.data(rentalPackagePrice).build();

		} else {
			rentalPackagePriceDao.deleteById(rentalPackagePrice.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(rentalPackagePrice).build();
		}
	}

	public BaseMessageResponse<Object> updateRentalPackagePrice(RentalPackagePriceDto rentalPackagePriceDto) {
		RentalPackagePrice oldRentalPackagePrice = rentalPackagePriceDao.findById(rentalPackagePriceDto.getId()).get();

		if (oldRentalPackagePrice.getId() != null) {
			oldRentalPackagePrice.setProcessId(rentalPackagePriceDto.getProcessId());
			oldRentalPackagePrice.setCountryId(rentalPackagePriceDto.getCountryId());
			oldRentalPackagePrice.setStateId(rentalPackagePriceDto.getStateId());
			oldRentalPackagePrice.setCityId(rentalPackagePriceDto.getCityId());
			oldRentalPackagePrice.setVehicleId(rentalPackagePriceDto.getVehicleId());
			oldRentalPackagePrice.setPackagePrice(rentalPackagePriceDto.getPackagePrice());
			oldRentalPackagePrice.setAdditionalKmPrice(rentalPackagePriceDto.getAdditionalKmPrice());
			oldRentalPackagePrice.setAdditionalMinPrice(rentalPackagePriceDto.getAdditionalMinPrice());

			if (rentalPackagePriceDto.getIsActive() == NumbersConstant.ONE.value)
				oldRentalPackagePrice.setIsActive(true);
			else
				oldRentalPackagePrice.setIsActive(false);

			RentalPackagePrice savedRentalPackagePrice = rentalPackagePriceDao.save(oldRentalPackagePrice);
			return BaseMessageResponse.builder().message("success").status(true).data(savedRentalPackagePrice).build();
		} else {
			return BaseMessageResponse.builder().message("rentalPackagePrice not present").status(false)
					.data(oldRentalPackagePrice).build();
		}
	}

	public BaseMessageResponse<Object> rentalPackagePriceById(Long rentalPackagePriceId) {
		Optional<RentalPackagePrice> rentalPackagePrice = rentalPackagePriceDao.findById(rentalPackagePriceId);

		if (!rentalPackagePrice.isPresent()) {
			return BaseMessageResponse.builder().message("rentalPackagePrice not present").status(false)
					.data(rentalPackagePrice).build();

		} else {
			RentalPackagePriceDto sDto = new RentalPackagePriceDto();

			Optional<Country> country = countryDao.findById(rentalPackagePrice.get().getCountryId());
			Optional<State> state = stateDao.findById(rentalPackagePrice.get().getStateId());
			Optional<City> city = cityDao.findById(rentalPackagePrice.get().getCityId());
			Optional<Process> process = processDao.findById(rentalPackagePrice.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(rentalPackagePrice.get().getVehicleId());

			sDto.setId(rentalPackagePrice.get().getId());
			sDto.setProcessId(rentalPackagePrice.get().getProcessId());
			sDto.setCountryId(rentalPackagePrice.get().getCountryId());
			sDto.setStateId(rentalPackagePrice.get().getStateId());
			sDto.setCityId(rentalPackagePrice.get().getCityId());
			sDto.setVehicleId(rentalPackagePrice.get().getVehicleId());

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

			sDto.setPackagePrice(rentalPackagePrice.get().getPackagePrice());
			sDto.setAdditionalKmPrice(rentalPackagePrice.get().getAdditionalKmPrice());
			sDto.setAdditionalMinPrice(rentalPackagePrice.get().getAdditionalMinPrice());
			if (rentalPackagePrice.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
