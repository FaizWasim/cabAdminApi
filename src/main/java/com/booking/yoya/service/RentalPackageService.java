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
import com.booking.yoya.dao.RentalPackageDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dto.RentalPackageDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.RentalPackage;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class RentalPackageService {

	@Autowired
	private RentalPackageDao rentalPackageDao;

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

	public BaseMessageResponse<Object> createNewRentalPackage(RentalPackage rentalPackage) {

		RentalPackage savedRentalPackage = rentalPackageDao.save(rentalPackage);
		return BaseMessageResponse.builder().message("success").status(true).data(savedRentalPackage).build();
	}

	public BaseMessageResponse<Object> getAllRentalPackages() {

		List<RentalPackage> rentalPackageList = rentalPackageDao.findAll();
		if (rentalPackageList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(rentalPackageList).build();
		} else {
			List<RentalPackageDto> rentalPackageDtoList = rentalPackageList.stream()
					.map(new Function<RentalPackage, RentalPackageDto>() {
						@Override
						public RentalPackageDto apply(RentalPackage s) {
							RentalPackageDto sDto = new RentalPackageDto();

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
							sDto.setHr(s.getHr());
							sDto.setKm(s.getKm());

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

							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);
							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(rentalPackageDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteRentalPackage(Long rentalPackageId) {
		Optional<RentalPackage> rentalPackage = rentalPackageDao.findById(rentalPackageId);

		if (!rentalPackage.isPresent()) {
			return BaseMessageResponse.builder().message("rentalPackage not present").status(false).data(rentalPackage)
					.build();

		} else {
			rentalPackageDao.deleteById(rentalPackage.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(rentalPackage).build();
		}
	}

	public BaseMessageResponse<Object> updateRentalPackage(RentalPackageDto rentalPackageDto) {
		RentalPackage oldRentalPackage = rentalPackageDao.findById(rentalPackageDto.getId()).get();

		if (oldRentalPackage.getId() != null) {
			oldRentalPackage.setProcessId(rentalPackageDto.getProcessId());
			oldRentalPackage.setCountryId(rentalPackageDto.getCountryId());
			oldRentalPackage.setStateId(rentalPackageDto.getStateId());
			oldRentalPackage.setCityId(rentalPackageDto.getCityId());
			oldRentalPackage.setVehicleId(rentalPackageDto.getVehicleId());
			oldRentalPackage.setHr(rentalPackageDto.getHr());
			oldRentalPackage.setKm(rentalPackageDto.getKm());
			if (rentalPackageDto.getIsActive() == NumbersConstant.ONE.value)
				oldRentalPackage.setIsActive(true);
			else
				oldRentalPackage.setIsActive(false);

			RentalPackage savedRentalPackage = rentalPackageDao.save(oldRentalPackage);
			return BaseMessageResponse.builder().message("success").status(true).data(savedRentalPackage).build();
		} else {
			return BaseMessageResponse.builder().message("rentalPackage not present").status(false)
					.data(oldRentalPackage).build();
		}
	}

	public BaseMessageResponse<Object> rentalPackageById(Long rentalPackageId) {
		Optional<RentalPackage> rentalPackage = rentalPackageDao.findById(rentalPackageId);

		if (!rentalPackage.isPresent()) {
			return BaseMessageResponse.builder().message("rentalPackage not present").status(false).data(rentalPackage)
					.build();

		} else {
			RentalPackageDto sDto = new RentalPackageDto();

			Optional<Country> country = countryDao.findById(rentalPackage.get().getCountryId());
			Optional<State> state = stateDao.findById(rentalPackage.get().getStateId());
			Optional<City> city = cityDao.findById(rentalPackage.get().getCityId());
			Optional<Process> process = processDao.findById(rentalPackage.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(rentalPackage.get().getVehicleId());

			sDto.setId(rentalPackage.get().getId());
			sDto.setProcessId(rentalPackage.get().getProcessId());
			sDto.setCountryId(rentalPackage.get().getCountryId());
			sDto.setStateId(rentalPackage.get().getStateId());
			sDto.setCityId(rentalPackage.get().getCityId());
			sDto.setVehicleId(rentalPackage.get().getVehicleId());

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

			sDto.setHr(rentalPackage.get().getHr());
			sDto.setKm(rentalPackage.get().getKm());
			if (rentalPackage.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
