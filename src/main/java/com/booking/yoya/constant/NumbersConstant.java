package com.booking.yoya.constant;

public enum NumbersConstant {

	ZERO(0), ONE(1), TWO(2),
	// ...
	THREE(3), SIX(6), SEVEN(7), EIGHT(8), TEN(10), TWELVE(12), EIGHTEEN(18), TWENTY(24), TWENTY_FOUR(24),

	SIXTY(60),

	THOUSAND(1000), DEFAULT_COUNTRYCODE(91), HUNDRED(100), NINITHOUSANDNIGHTYNINE(9999);

	public final int value;

	private NumbersConstant(int d) {
		this.value = d;
	}

}
