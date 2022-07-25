package com.booking.yoya.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.ProcessDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dto.VehicleDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.service.aws.s3.AmazonS3ClientService;

@Service
public class VehicleService {

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

	@Autowired
	private AmazonS3ClientService amazonS3ClientService;

	public BaseMessageResponse<Object> createNewVehicle(VehicleDto vehicleDto) {
		Optional<Vehicle> vehicleName = vehicleDao.findByVehicleName(vehicleDto.getName());
		try {
			if (vehicleName.isPresent()) {
				return BaseMessageResponse.builder().message("vehicle already present").status(true).data(vehicleName)
						.build();

			} else {
				System.out.println("vehicleDto ---" + vehicleDto.toString());
				Vehicle vehicle = new Vehicle();
				vehicle.setProcessId(vehicleDto.getProcessId());
				vehicle.setCountryId(vehicleDto.getCountryId());
				vehicle.setStateId(vehicleDto.getStateId());
				vehicle.setCityId(vehicleDto.getCityId());
				vehicle.setName(vehicleDto.getName());
				vehicle.setVehicleDescription(vehicleDto.getVehicleDescription());

				if (!vehicleDto.getIcon().isEmpty()) {
					MultipartFile icon = vehicleDto.getIcon();
					String folder = StringsConstant.ICONS.value;
					UUID randomUUID = UUID.randomUUID();

					boolean status = this.amazonS3ClientService.uploadFileToS3Bucket(icon, true, folder, randomUUID);
					Map<String, String> response = new HashMap();

					if (status) {
						String path = folder + randomUUID + icon.getOriginalFilename();
						vehicle.setIcon(path);
					}

				} else {
					vehicle.setIcon(StringsConstant.EMPTY.value);
				}
				System.out.println("vehicle ---" + vehicle.toString());
				Vehicle savedVehicle = vehicleDao.save(vehicle);
				return BaseMessageResponse.builder().message("success").status(true).data(savedVehicle).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build();

		}
	}

	public BaseMessageResponse<Object> getAllVehicles() {

		List<Vehicle> vehicleList = vehicleDao.findAll();
		if (vehicleList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(vehicleList).build();
		} else {
			List<VehicleDto> vehicleDtoList = vehicleList.stream().map(new Function<Vehicle, VehicleDto>() {
				@Override
				public VehicleDto apply(Vehicle s) {
					VehicleDto sDto = new VehicleDto();

					Optional<Country> country = countryDao.findById(s.getCountryId());
					Optional<State> state = stateDao.findById(s.getStateId());
					Optional<City> city = cityDao.findById(s.getCityId());
					Optional<Process> process = processDao.findById(s.getProcessId());

					sDto.setId(s.getId());
					sDto.setProcessId(s.getProcessId());
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

					if (!process.isPresent())
						sDto.setProcessName(StringsConstant.EMPTY.value);
					else
						sDto.setProcessName(process.get().getName());

					sDto.setName(s.getName());
					sDto.setIconPath(StringsConstant.S3URL.value + s.getIcon());
					sDto.setVehicleDescription(s.getVehicleDescription());
					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(vehicleDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteVehicle(Long vehicleId) {
		Optional<Vehicle> vehicle = vehicleDao.findById(vehicleId);

		if (!vehicle.isPresent()) {
			return BaseMessageResponse.builder().message("vehicle not present").status(false).data(vehicle).build();

		} else {
			vehicleDao.deleteById(vehicle.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(vehicle).build();
		}
	}

	public BaseMessageResponse<Object> updateVehicle(VehicleDto vehicle) {
		Vehicle oldVehicle = vehicleDao.findById(vehicle.getId()).get();
		try {
			if (vehicle.getId() != null) {
				oldVehicle.setProcessId(vehicle.getProcessId());
				oldVehicle.setCountryId(vehicle.getCountryId());
				oldVehicle.setStateId(vehicle.getStateId());
				oldVehicle.setCityId(vehicle.getCityId());
				oldVehicle.setName(vehicle.getName());
//				oldVehicle.setIcon(vehicle.getIcon());

				if (!vehicle.getIcon().isEmpty()) {
					MultipartFile icon = vehicle.getIcon();
					String folder = StringsConstant.ICONS.value;
					UUID randomUUID = UUID.randomUUID();

					boolean status = this.amazonS3ClientService.uploadFileToS3Bucket(icon, true, folder, randomUUID);
					Map<String, String> response = new HashMap();

					if (status) {
						String path = folder + "/" + randomUUID + icon.getOriginalFilename();
						oldVehicle.setIcon(path);
					}

				} else {
					oldVehicle.setIcon(vehicle.getIconPath());
				}

				oldVehicle.setVehicleDescription(vehicle.getVehicleDescription());
				if (vehicle.getIsActive() == NumbersConstant.ONE.value)
					oldVehicle.setIsActive(true);
				else
					oldVehicle.setIsActive(false);
				Vehicle savedVehicle = vehicleDao.save(oldVehicle);
				return BaseMessageResponse.builder().message("success").status(true).data(savedVehicle).build();
			} else {
				return BaseMessageResponse.builder().message("vehicle not present").status(false).data(vehicle).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build();

		}

	}

	public BaseMessageResponse<Object> vehicleById(Long vehicleId) {
		Optional<Vehicle> vehicle = vehicleDao.findById(vehicleId);
		try {
			if (!vehicle.isPresent()) {
				return BaseMessageResponse.builder().message("vehicle not present").status(false).data(vehicle).build();

			} else {
				VehicleDto sDto = new VehicleDto();

				Optional<Country> country = countryDao.findById(vehicle.get().getCountryId());
				Optional<State> state = stateDao.findById(vehicle.get().getStateId());
				Optional<City> city = cityDao.findById(vehicle.get().getCityId());
				Optional<Process> process = processDao.findById(vehicle.get().getProcessId());

				sDto.setId(vehicle.get().getId());
				sDto.setProcessId(vehicle.get().getProcessId());
				sDto.setCountryId(vehicle.get().getCountryId());
				sDto.setStateId(vehicle.get().getStateId());
				sDto.setCityId(vehicle.get().getCityId());

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

				sDto.setName(vehicle.get().getName());

				if (vehicle.get().getIcon() != null)
					sDto.setIconPath(StringsConstant.S3URL.value + vehicle.get().getIcon());
				else
					sDto.setIconPath(StringsConstant.EMPTY.value);

				sDto.setVehicleDescription(vehicle.get().getVehicleDescription());
				if (vehicle.get().getIsActive().equals(true))
					sDto.setIsActive(NumbersConstant.ONE.value);
				else
					sDto.setIsActive(NumbersConstant.ZERO.value);
				return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("vehicle not present").status(false).data(e.getMessage())
					.build();

		}
	}

}
