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
import com.booking.yoya.dao.LanguageDao;
import com.booking.yoya.dto.LanguageDto;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Language;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class LanguageService {

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private CountryDao countryDao;

	public BaseMessageResponse<Object> createNewLanguage(Language language) {
		Optional<Language> languageName = languageDao.findByLanguageName(language.getLanguageName());

		if (languageName.isPresent()) {
			return BaseMessageResponse.builder().message("language already present").status(true).data(languageName)
					.build();

		} else {
			Language savedLanguage = languageDao.save(language);
			return BaseMessageResponse.builder().message("success").status(true).data(savedLanguage).build();
		}
	}

	public BaseMessageResponse<Object> getAllLanguages() {

		List<Language> languageList = languageDao.findAll();
		if (languageList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(languageList).build();
		} else {
			List<LanguageDto> languageDtoList = languageList.stream().map(new Function<Language, LanguageDto>() {
				@Override
				public LanguageDto apply(Language c) {
					LanguageDto cDto = new LanguageDto();

					cDto.setId(c.getId());
					cDto.setCountryId(c.getCountryId());
					Optional<Country> country = countryDao.findById(c.getCountryId());
					if (!country.isPresent())
						cDto.setCountryName(StringsConstant.EMPTY.value);
					else
						cDto.setCountryName(country.get().getCountryName());
					cDto.setLanguageCode(c.getLanguageCode());
					cDto.setLanguageName(c.getLanguageName());
					if (c.getIsActive().equals(true))
						cDto.setIsActive(NumbersConstant.ONE.value);
					else
						cDto.setIsActive(NumbersConstant.ZERO.value);

					return cDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(languageDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteLanguage(Long languageId) {
		Optional<Language> language = languageDao.findById(languageId);

		if (!language.isPresent()) {
			return BaseMessageResponse.builder().message("language not present").status(false).data(language).build();

		} else {
			languageDao.deleteById(language.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(language).build();
		}
	}

	public BaseMessageResponse<Object> updateLanguage(LanguageDto language) {
		Language oldLanguage = languageDao.findById(language.getId()).get();

		if (oldLanguage.getId() != null) {
			oldLanguage.setCountryId(language.getCountryId());
			oldLanguage.setLanguageCode(language.getLanguageCode());
			oldLanguage.setLanguageName(language.getLanguageName());

			if (language.getIsActive() == NumbersConstant.ONE.value)
				oldLanguage.setIsActive(true);
			else
				oldLanguage.setIsActive(false);

			Language savedLanguage = languageDao.save(oldLanguage);
			return BaseMessageResponse.builder().message("success").status(true).data(savedLanguage).build();
		} else {
			return BaseMessageResponse.builder().message("language not present").status(false).data(language).build();
		}
	}

	public BaseMessageResponse<Object> languageById(Long languageId) {
		Optional<Language> language = languageDao.findById(languageId);

		if (!language.isPresent()) {
			return BaseMessageResponse.builder().message("language not present").status(false).data(language).build();

		} else {
			LanguageDto cDto = new LanguageDto();
			cDto.setId(language.get().getId());
			cDto.setCountryId(language.get().getCountryId());
			cDto.setLanguageName(language.get().getLanguageName());
			cDto.setLanguageCode(language.get().getLanguageCode());
			Optional<Country> country = countryDao.findById(language.get().getCountryId());
			if (!country.isPresent())
				cDto.setCountryName(StringsConstant.EMPTY.value);
			else
				cDto.setCountryName(country.get().getCountryName());

			if (language.get().getIsActive().equals(true))
				cDto.setIsActive(NumbersConstant.ONE.value);
			else
				cDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(cDto).build();
		}
	}

}
