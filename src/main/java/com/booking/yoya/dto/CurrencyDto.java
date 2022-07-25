package com.booking.yoya.dto;

public class CurrencyDto {

	private Long id;

	private String name;
	private String symbol;
	private String code;
	private int isActive;

	public CurrencyDto() {
		// TODO Auto-generated constructor stub
	}

	public CurrencyDto(Long id, String name, String symbol, String code, int isActive) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.code = code;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CurrencyDto [id=" + id + ", name=" + name + ", symbol=" + symbol + ", code=" + code + ", isActive="
				+ isActive + "]";
	}

}
