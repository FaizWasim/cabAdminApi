package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.dao.FaqCategoryDao;
import com.booking.yoya.dto.FaqCategoryDto;
import com.booking.yoya.model.FaqCategory;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class FaqCategoryService {

	@Autowired
	private FaqCategoryDao faqCategoryDao;

	public BaseMessageResponse<Object> createNewFaqCategory(FaqCategory faqCategory) {

		FaqCategory savedFaqCategory = faqCategoryDao.save(faqCategory);
		return BaseMessageResponse.builder().message("success").status(true).data(savedFaqCategory).build();
	}

	public BaseMessageResponse<Object> getAllFaqCategory() {

		List<FaqCategory> faqCategoryList = faqCategoryDao.findAll();
		if (faqCategoryList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(faqCategoryList).build();
		} else {
			List<FaqCategoryDto> faqCategoryDtoList = faqCategoryList.stream()
					.map(new Function<FaqCategory, FaqCategoryDto>() {
						@Override
						public FaqCategoryDto apply(FaqCategory s) {
							FaqCategoryDto sDto = new FaqCategoryDto();

							sDto.setFaqCategoryId(s.getFaqCategoryId());
							sDto.setFaqFor(s.getFaqFor());
							sDto.setCategoryName(s.getCategoryName());
							if (s.getIsActive().equals(true))
								sDto.setIsActive(NumbersConstant.ONE.value);
							else
								sDto.setIsActive(NumbersConstant.ZERO.value);

							return sDto;
						}
					}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(faqCategoryDtoList).build();

		}

	}

	public BaseMessageResponse<Object> deleteFaqCategory(Long faqCategoryId) {
		Optional<FaqCategory> faqCategory = faqCategoryDao.findById(faqCategoryId);

		if (!faqCategory.isPresent()) {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();

		} else {
			faqCategoryDao.deleteById(faqCategory.get().getFaqCategoryId());
			return BaseMessageResponse.builder().message("success").status(true).data(faqCategory).build();
		}
	}

	public BaseMessageResponse<Object> updateFaqCategory(FaqCategoryDto faqCategory) {
		FaqCategory oldFaqCategory = faqCategoryDao.findById(faqCategory.getFaqCategoryId()).get();

		if (faqCategory.getFaqCategoryId() != null) {
			oldFaqCategory.setFaqFor(faqCategory.getFaqFor());
			oldFaqCategory.setCategoryName(faqCategory.getCategoryName());

			if (faqCategory.getIsActive() == NumbersConstant.ONE.value)
				oldFaqCategory.setIsActive(true);
			else
				oldFaqCategory.setIsActive(false);

			FaqCategory savedFaqCategory = faqCategoryDao.save(oldFaqCategory);
			return BaseMessageResponse.builder().message("success").status(true).data(savedFaqCategory).build();
		} else {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();
		}
	}

	public BaseMessageResponse<Object> faqCategoryById(Long faqCategoryId) {
		Optional<FaqCategory> faqCategory = faqCategoryDao.findById(faqCategoryId);

		if (!faqCategory.isPresent()) {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();

		} else {
			FaqCategoryDto sDto = new FaqCategoryDto();
			sDto.setFaqCategoryId(faqCategory.get().getFaqCategoryId());
			sDto.setFaqFor(faqCategory.get().getFaqFor());
			sDto.setCategoryName(faqCategory.get().getCategoryName());
			if (faqCategory.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);
			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
