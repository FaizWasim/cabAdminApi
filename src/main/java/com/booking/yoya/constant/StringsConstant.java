package com.booking.yoya.constant;

public enum StringsConstant {

	EMPTY(""), COMMA(","), COLON(":"), UNDER_SCORE("_"), HYPHEN("-"), ROLE_ADMIN("ADMIN"), ROLE_USER("USER"),
	ROLE_AGENT("AGENT"), FORM_DATE_FORMAT("dd/MM/uuuu HH:mm"), STATUS_NEW("n"), STATUS_CONFIRMED("c"), STATUS_END("e"),
	STATUS_PENDING("0"), STATUS_ACTIVE("1"), STATUS_STARTED("s"), STATUS_CANCEL("x"), NO("n"), YES("y"), BOOKED("b"),
	NAME("name"), SURNAME("surname"), OTP("otp"), TYPE("type"),

	DATE_TIME_LOCAL("yyyy-MM-dd'T'HH:mm"), REQUESTURL("requestUrl"), SUCCESS("success"), CONTENT("content/"),
	ICONS("icons/"), DOCUMENTS("documents/"), IMAGES("images/"), S3URL("https://cabicons.s3.ap-south-1.amazonaws.com/");
	;

	public final String value;

	private StringsConstant(String value) {
		this.value = value;
	}
}