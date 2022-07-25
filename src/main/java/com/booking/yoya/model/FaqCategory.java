package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "faq_category")
//@Where(clause = "is_active = true")
public class FaqCategory extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faq_category_id")
	private Long faqCategoryId;

	@Column(name = "faq_for", nullable = false)
	private Long faqFor;

	private String categoryName;

	public FaqCategory() {
		// TODO Auto-generated constructor stub
	}

	public FaqCategory(Long faqCategoryId, Long faqFor, String categoryName) {
		super();
		this.faqCategoryId = faqCategoryId;
		this.faqFor = faqFor;
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "FaqCategory [faqCategoryId=" + faqCategoryId + ", faqFor=" + faqFor + ", categoryName=" + categoryName
				+ "]";
	}

}
