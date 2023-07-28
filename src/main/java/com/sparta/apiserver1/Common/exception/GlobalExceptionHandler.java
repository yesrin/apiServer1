package com.sparta.apiserver1.Common.exception;

import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice 사용
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponseDto> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        ApiResponseDto ApiResponseDto = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                ApiResponseDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({DuplicateRequestException.class})
    public ResponseEntity<ApiResponseDto> DuplicateRequestException(DuplicateRequestException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                apiResponseDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiResponseDto> NotFoundException(NotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                apiResponseDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }
}
