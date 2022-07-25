package com.booking.yoya.dto;

public class FaqCategoryDto {

	private Long faqCategoryId;

	private Long faqFor;

	private String categoryName;
	private int isActive;

	public FaqCategoryDto() {
		// TODO Auto-generated constructor stub
	}

	public FaqCategoryDto(Long faqCategoryId, Long faqFor, String categoryName, int isActive) {
		super();
		this.faqCategoryId = faqCategoryId;
		this.faqFor = faqFor;
		this.categoryName = categoryName;
		this.isActive = isActive;
	}

	public Long getFaqCategoryId() {
		return faqCategoryId;
	}

	public void setFaqCategoryId(Long faqCategoryId) {
		this.faqCategoryId = faqCategoryId;
	}

	public Long getFaqFor() {
		return faqFor;
	}

	public void setFaqFor(Long faqFor) {
		this.faqFor = faqFor;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FaqCategoryDto [faqCategoryId=" + faqCategoryId + ", faqFor=" + faqFor + ", categoryName="
				+ categoryName + ", isActive=" + isActive + "]";
	}

}
