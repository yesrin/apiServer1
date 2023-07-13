package com.sparta.apiserver1.Common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestApiException {
    private String errorMessage;
    private int statusCode;

    public RestApiException(String msg, Integer statusCode) {
        this.errorMessage = msg;
        this.statusCode = statusCode;
    }
}
