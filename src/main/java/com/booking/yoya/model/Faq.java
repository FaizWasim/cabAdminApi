package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

@Entity
@Table(name = "faq")
//@Where(clause = "is_active = true")
public class Faq extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "faq_for", nullable = false)
	private Long faqFor;

	private String question;

	private String answer;

	public Faq() {
		// TODO Auto-generated constructor stub
	}

	public Faq(Long id, Long faqFor, String question, String answer) {
		super();
		this.id = id;
		this.faqFor = faqFor;
		this.question = question;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaqFor() {
		return faqFor;
	}

	public void setFaqFor(Long faqFor) {
		this.faqFor = faqFor;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "FaqCategory [id=" + id + ", faqFor=" + faqFor + ", question=" + question + ", answer=" + answer + "]";
	}

}
