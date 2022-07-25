package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.TaxDao;
import com.booking.yoya.dto.TaxDto;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Tax;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class TaxService {

	@Autowired
	private TaxDao taxDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private StateDao stateDao;

	public BaseMessageResponse<Object> createNewTax(Tax tax) {
		Optional<Tax> taxName = taxDao.findByTaxName(tax.getTaxName());

		if (taxName.isPresent()) {
			return BaseMessageResponse.builder().message("tax already present").status(true).data(taxName).build();

		} else {
			Tax savedTax = taxDao.save(tax);
			return BaseMessageResponse.builder().message("success").status(true).data(savedTax).build();
		}
	}

	public BaseMessageResponse<Object> getAllTaxs() {

		List<Tax> taxList = taxDao.findAll();
		if (taxList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(taxList).build();
		} else {
			List<TaxDto> taxDtoList = taxList.stream().map(new Function<Tax, TaxDto>() {
				@Override
				public TaxDto apply(Tax s) {
					TaxDto sDto = new TaxDto();

					Optional<Country> country = countryDao.findById(s.getCountryId());
					Optional<State> state = stateDao.findById(s.getStateId());

					sDto.setId(s.getId());
					sDto.setCountryId(s.getCountryId());
					sDto.setStateId(s.getStateId());
					if (!country.isPresent())
						sDto.setCountryName(StringsConstant.EMPTY.value);
					else
						sDto.setCountryName(country.get().getCountryName());

					if (!state.isPresent())
						sDto.setStateName(StringsConstant.EMPTY.value);
					else
						sDto.setStateName(state.get().getStateName());

					sDto.setTaxName(s.getTaxName());
					sDto.setTaxPrice(s.getTaxPrice());
					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(taxDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteTax(Long taxId) {
		Optional<Tax> tax = taxDao.findById(taxId);

		if (!tax.isPresent()) {
			return BaseMessageResponse.builder().message("tax not present").status(false).data(tax).build();

		} else {
			taxDao.deleteById(tax.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(tax).build();
		}
	}

	public BaseMessageResponse<Object> updateTax(TaxDto tax) {
		Tax oldTax = taxDao.findById(tax.getId()).get();

		if (tax.getId() != null) {
			oldTax.setCountryId(tax.getCountryId());
			oldTax.setStateId(tax.getStateId());
			oldTax.setTaxName(tax.getTaxName());
			oldTax.setTaxPrice(tax.getTaxPrice());
			if (tax.getIsActive() == NumbersConstant.ONE.value)
				oldTax.setIsActive(true);
			else
				oldTax.setIsActive(false);
			Tax savedTax = taxDao.save(oldTax);
			return BaseMessageResponse.builder().message("success").status(true).data(savedTax).build();
		} else {
			return BaseMessageResponse.builder().message("tax not present").status(false).data(tax).build();
		}
	}

	public BaseMessageResponse<Object> taxById(Long taxId) {
		Optional<Tax> tax = taxDao.findById(taxId);

		if (!tax.isPresent()) {
			return BaseMessageResponse.builder().message("tax not present").status(false).data(tax).build();

		} else {
			TaxDto sDto = new TaxDto();
			Optional<Country> country = countryDao.findById(tax.get().getCountryId());
			Optional<State> state = stateDao.findById(tax.get().getStateId());
			sDto.setId(tax.get().getId());
			sDto.setCountryId(tax.get().getCountryId());
			sDto.setStateId(tax.get().getStateId());
			if (!country.isPresent())
				sDto.setCountryName(StringsConstant.EMPTY.value);
			else
				sDto.setCountryName(country.get().getCountryName());

			if (!state.isPresent())
				sDto.setStateName(StringsConstant.EMPTY.value);
			else
				sDto.setStateName(state.get().getStateName());

			sDto.setTaxName(tax.get().getTaxName());
			sDto.setTaxPrice(tax.get().getTaxPrice());
			if (tax.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
