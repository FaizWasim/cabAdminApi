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
import com.booking.yoya.dao.PushNotificationDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.VehicleDao;
import com.booking.yoya.dto.PushNotificationDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.PushNotification;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Vehicle;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class PushNotificationService {

	@Autowired
	private PushNotificationDao pushNotificationDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private ProcessDao processDao;

	@Autowired
	private VehicleDao vehicleDao;

	public BaseMessageResponse<Object> createNewPushNotification(PushNotification pushNotification) {
//		Optional<PushNotification> pushNotificationName = pushNotificationDao.findByPushNotificationName(pushNotification.getPushNotificationName());
//
//		if (pushNotificationName.isPresent()) {
//			return BaseMessageResponse.builder().message("pushNotification already present").status(true).data(pushNotificationName).build();
//
//		} else {
		PushNotification savedPushNotification = pushNotificationDao.save(pushNotification);
		return BaseMessageResponse.builder().message("success").status(true).data(savedPushNotification).build();
//		}
	}

	public BaseMessageResponse<Object> getAllPushNotifications() {

		List<PushNotification> pushNotificationList = pushNotificationDao.findAll();
		if (pushNotificationList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(pushNotificationList).build();
		} else {
			List<PushNotificationDto> pushNotificationDtoList = pushNotificationList.stream()
					.map(new Function<PushNotification, PushNotificationDto>() {
						@Override
						public PushNotificationDto apply(PushNotification s) {
							PushNotificationDto sDto = new PushNotificationDto();

							Optional<Country> country = countryDao.findById(s.getCountryId());
							Optional<State> state = stateDao.findById(s.getStateId());
							Optional<City> city = cityDao.findById(s.getCityId());
							Optional<Process> process = processDao.findById(s.getProcessId());
							Optional<Vehicle> vehicle = vehicleDao.findById(s.getVehicleId());

							sDto.setId(s.getId());
							sDto.setSendTo(s.getSendTo());
							sDto.setCountryId(s.getCountryId());
							sDto.setStateId(s.getStateId());
							sDto.setCityId(s.getCityId());
							sDto.setProcessId(s.getProcessId());
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

							sDto.setSubject(s.getSubject());
							sDto.setText(s.getText());
							sDto.setImage(s.getImage());
							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);

							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(pushNotificationDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deletePushNotification(Long pushNotificationId) {
		Optional<PushNotification> pushNotification = pushNotificationDao.findById(pushNotificationId);

		if (!pushNotification.isPresent()) {
			return BaseMessageResponse.builder().message("pushNotification not present").status(false)
					.data(pushNotification).build();

		} else {
			pushNotificationDao.deleteById(pushNotification.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(pushNotification).build();
		}
	}

	public BaseMessageResponse<Object> updatePushNotification(PushNotificationDto pushNotification) {
		PushNotification oldPushNotification = pushNotificationDao.findById(pushNotification.getId()).get();

		if (pushNotification.getId() != null) {
			oldPushNotification.setCountryId(pushNotification.getCountryId());
			oldPushNotification.setStateId(pushNotification.getStateId());

			oldPushNotification.setCityId(pushNotification.getCityId());
			oldPushNotification.setProcessId(pushNotification.getProcessId());
			oldPushNotification.setVehicleId(pushNotification.getVehicleId());
			oldPushNotification.setSubject(pushNotification.getSubject());
			oldPushNotification.setText(pushNotification.getText());
			oldPushNotification.setImage(pushNotification.getImage());
			if (pushNotification.getIsActive() == NumbersConstant.ONE.value)
				oldPushNotification.setIsActive(true);
			else
				oldPushNotification.setIsActive(false);

			PushNotification savedPushNotification = pushNotificationDao.save(oldPushNotification);
			return BaseMessageResponse.builder().message("success").status(true).data(savedPushNotification).build();
		} else {
			return BaseMessageResponse.builder().message("pushNotification not present").status(false)
					.data(pushNotification).build();
		}
	}

	public BaseMessageResponse<Object> pushNotificationById(Long pushNotificationId) {
		Optional<PushNotification> pushNotification = pushNotificationDao.findById(pushNotificationId);

		if (!pushNotification.isPresent()) {
			return BaseMessageResponse.builder().message("pushNotification not present").status(false)
					.data(pushNotification).build();

		} else {
			PushNotificationDto sDto = new PushNotificationDto();

			Optional<Country> country = countryDao.findById(pushNotification.get().getCountryId());
			Optional<State> state = stateDao.findById(pushNotification.get().getStateId());
			Optional<City> city = cityDao.findById(pushNotification.get().getCityId());
			Optional<Process> process = processDao.findById(pushNotification.get().getProcessId());
			Optional<Vehicle> vehicle = vehicleDao.findById(pushNotification.get().getVehicleId());

			sDto.setId(pushNotification.get().getId());
			sDto.setCountryId(pushNotification.get().getCountryId());
			sDto.setStateId(pushNotification.get().getStateId());

			sDto.setCityId(pushNotification.get().getCityId());
			sDto.setProcessId(pushNotification.get().getProcessId());
			sDto.setVehicleId(pushNotification.get().getVehicleId());

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

			sDto.setSubject(pushNotification.get().getSubject());
			sDto.setText(pushNotification.get().getText());
			sDto.setImage(pushNotification.get().getImage());
			if (pushNotification.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
