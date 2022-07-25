package com.booking.yoya.dto;

public class FaqDto {

	private Long id;

	private Long faqFor;

	private String question;

	private String answer;
	private int isActive;

	public FaqDto() {
		// TODO Auto-generated constructor stub
	}

	public FaqDto(Long id, Long faqFor, String question, String answer, int isActive) {
		super();
		this.id = id;
		this.faqFor = faqFor;
		this.question = question;
		this.answer = answer;
		this.isActive = isActive;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FaqDto [id=" + id + ", faqFor=" + faqFor + ", question=" + question + ", answer=" + answer
				+ ", isActive=" + isActive + "]";
	}

}
