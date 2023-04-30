package com.spectrocoin.currencyconverter.rest.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
@Builder
public class ErrorResponse {

    @NonNull
    private List<String> messages;

    public static ErrorResponse of(String errorMessage) {
        return ErrorResponse.builder()
                .messages(Collections.singletonList(errorMessage))
                .build();
    }
}