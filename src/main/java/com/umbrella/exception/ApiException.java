package com.umbrella.exception;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class ApiException {
    private final String error;
    private final LocalDateTime dateTime;
    private final HttpStatus status;
    private final Integer statusCode;

    public String getError() {
        return error;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
