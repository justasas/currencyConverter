package com.spectrocoin.currencyconverter.domain.exceptions;

public class CurrencyConvertException extends RuntimeException {
    public CurrencyConvertException(String message, Throwable cause) {
        super(message, cause);
    }

}
