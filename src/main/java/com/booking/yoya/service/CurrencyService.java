package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.dao.CurrencyDao;
import com.booking.yoya.dto.CurrencyDto;
import com.booking.yoya.model.Currency;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;

	public BaseMessageResponse<Object> createNewCurrency(Currency currency) {
		Optional<Currency> currencyName = currencyDao.findByCurrencyName(currency.getName());

		if (currencyName.isPresent()) {
			return BaseMessageResponse.builder().message("currency already present").status(true).data(currencyName)
					.build();

		} else {
			Currency savedCurrency = currencyDao.save(currency);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCurrency).build();
		}
	}

	public BaseMessageResponse<Object> getAllCurrencys() {

		List<Currency> currencyList = currencyDao.findAll();
		if (currencyList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(currencyList).build();
		} else {
			List<CurrencyDto> currencyDtoList = currencyList.stream().map(new Function<Currency, CurrencyDto>() {
				@Override
				public CurrencyDto apply(Currency s) {
					CurrencyDto sDto = new CurrencyDto();

					sDto.setId(s.getId());
					sDto.setName(s.getName());
					sDto.setSymbol(s.getSymbol());
					sDto.setCode(s.getCode());
					if (s.getIsActive().equals(true))
						sDto.setIsActive(NumbersConstant.ONE.value);
					else
						sDto.setIsActive(NumbersConstant.ZERO.value);

					return sDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(currencyDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteCurrency(Long currencyId) {
		Optional<Currency> currency = currencyDao.findById(currencyId);

		if (!currency.isPresent()) {
			return BaseMessageResponse.builder().message("currency not present").status(false).data(currency).build();

		} else {
			currencyDao.deleteById(currency.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(currency).build();
		}
	}

	public BaseMessageResponse<Object> updateCurrency(CurrencyDto currency) {
		Currency oldCurrency = currencyDao.findById(currency.getId()).get();

		if (currency.getId() != null) {
			oldCurrency.setName(currency.getName());
			oldCurrency.setSymbol(currency.getSymbol());
			oldCurrency.setCode(currency.getCode());

			if (currency.getIsActive() == NumbersConstant.ONE.value)
				oldCurrency.setIsActive(true);
			else
				oldCurrency.setIsActive(false);

			Currency savedCurrency = currencyDao.save(oldCurrency);
			return BaseMessageResponse.builder().message("success").status(true).data(savedCurrency).build();
		} else {
			return BaseMessageResponse.builder().message("currency not present").status(false).data(currency).build();
		}
	}

	public BaseMessageResponse<Object> currencyById(Long currencyId) {
		Optional<Currency> currency = currencyDao.findById(currencyId);

		if (!currency.isPresent()) {
			return BaseMessageResponse.builder().message("currency not present").status(false).data(currency).build();

		} else {
			CurrencyDto sDto = new CurrencyDto();
			sDto.setId(currency.get().getId());
			sDto.setName(currency.get().getName());
			sDto.setSymbol(currency.get().getSymbol());
			sDto.setCode(currency.get().getCode());

			if (currency.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
