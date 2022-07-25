package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.dao.FaqDao;
import com.booking.yoya.dto.FaqDto;
import com.booking.yoya.model.Faq;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class FaqService {

	@Autowired
	private FaqDao faqCategoryDao;

	public BaseMessageResponse<Object> createNewFaq(Faq faqCategory) {

		Faq savedFaq = faqCategoryDao.save(faqCategory);
		return BaseMessageResponse.builder().message("success").status(true).data(savedFaq).build();
	}

	public BaseMessageResponse<Object> getAllFaq() {

		List<Faq> faqCategoryList = faqCategoryDao.findAll();
		if (faqCategoryList.isEmpty()) {
			return BaseMessageResponse.builder().message("success").status(false).data(faqCategoryList).build();
		} else {
			List<FaqDto> faqCategoryDtoList = faqCategoryList.stream().map(new Function<Faq, FaqDto>() {
				@Override
				public FaqDto apply(Faq s) {
					FaqDto sDto = new FaqDto();

					sDto.setId(s.getId());
					sDto.setFaqFor(s.getFaqFor());
					sDto.setQuestion(s.getQuestion());
					sDto.setAnswer(s.getAnswer());
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

	public BaseMessageResponse<Object> deleteFaq(Long faqCategoryId) {
		Optional<Faq> faqCategory = faqCategoryDao.findById(faqCategoryId);

		if (!faqCategory.isPresent()) {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();

		} else {
			faqCategoryDao.deleteById(faqCategory.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(faqCategory).build();
		}
	}

	public BaseMessageResponse<Object> updateFaq(FaqDto faqCategory) {
		Faq oldFaq = faqCategoryDao.findById(faqCategory.getId()).get();

		if (faqCategory.getId() != null) {
			oldFaq.setFaqFor(faqCategory.getFaqFor());
			oldFaq.setQuestion(faqCategory.getQuestion());
			oldFaq.setAnswer(faqCategory.getAnswer());
			if (faqCategory.getIsActive() == NumbersConstant.ONE.value)
				oldFaq.setIsActive(true);
			else
				oldFaq.setIsActive(false);

			Faq savedFaq = faqCategoryDao.save(oldFaq);
			return BaseMessageResponse.builder().message("success").status(true).data(savedFaq).build();
		} else {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();
		}
	}

	public BaseMessageResponse<Object> faqById(Long faqCategoryId) {
		Optional<Faq> faqCategory = faqCategoryDao.findById(faqCategoryId);

		if (!faqCategory.isPresent()) {
			return BaseMessageResponse.builder().message("faqCategory not present").status(false).data(faqCategory)
					.build();

		} else {
			FaqDto sDto = new FaqDto();
			sDto.setId(faqCategory.get().getId());
			sDto.setFaqFor(faqCategory.get().getFaqFor());
			sDto.setQuestion(faqCategory.get().getQuestion());
			sDto.setAnswer(faqCategory.get().getAnswer());

			if (faqCategory.get().getIsActive().equals(true))
				sDto.setIsActive(NumbersConstant.ONE.value);
			else
				sDto.setIsActive(NumbersConstant.ZERO.value);

			return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();
		}
	}

}
