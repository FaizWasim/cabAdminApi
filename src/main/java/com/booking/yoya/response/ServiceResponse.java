package com.booking.yoya.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceResponse<T> extends ResponseEntity<T> {
    public ServiceResponse(T data, HttpStatus httpStatus) {
        super(data, httpStatus);
    }

    public ServiceResponse(T data) {
        super(data, HttpStatus.OK);
    }
}
