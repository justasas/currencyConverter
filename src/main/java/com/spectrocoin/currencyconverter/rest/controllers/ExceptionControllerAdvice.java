package com.spectrocoin.currencyconverter.rest.controllers;

import com.spectrocoin.currencyconverter.rest.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler({Throwable.class})
    public ResponseEntity internalServerErrorHandler(Throwable throwable) {

        log.error("Server is unable to process the request: ", throwable);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.of("Server is unable to process the request"));
    }
}
