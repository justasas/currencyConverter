package com.spectrocoin.currencyconverter.domain.services;

import com.spectrocoin.currencyconverter.domain.dto.CurrencyExchangeCommand;
import com.spectrocoin.currencyconverter.domain.exceptions.CurrencyConvertException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyExchangeServiceTest {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Test
    void convert() {
        BigDecimal convertedAmount = currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                .from("EUR")
                .to("USD")
                .amount(new BigDecimal("10"))
                .build());

        assertEquals(new BigDecimal("8.095527220"), convertedAmount);
    }

    @Test
    void convertResultAmountShouldBePrecise() {

        BigDecimal convertedAmount = currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                .from("USD")
                .to("BTC")
                .amount(new BigDecimal("1.1"))
                .build());

        assertEquals(new BigDecimal("9480.29500010747910"), convertedAmount);
        assertEquals(CurrencyExchangeService.PRECISION + 1, convertedAmount.toString().length());
    }

    @Test
    void convertWithoutAmountShouldThrowException() {
        assertThrows(CurrencyConvertException.class, () -> {
            currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                    .from("USD")
                    .to("BTC")
                    .build());
        });
    }

    @Test
    void convertWithoutCurrencyFromShouldThrowException() {
        assertThrows(CurrencyConvertException.class, () -> {
            currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                    .to("BTC")
                    .amount(new BigDecimal("1.1"))
                    .build());
        });
    }

    @Test
    void convertWithoutCurrencyToShouldThrowException() {
        assertThrows(CurrencyConvertException.class, () -> {
            currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                    .from("USD")
                    .amount(new BigDecimal("1.1"))
                    .build());
        });
    }

    @Test
    void convertWithNotValidCurrencyShouldThrowException() {
        assertThrows(CurrencyConvertException.class, () -> {
            currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                    .from("NOT_VALID")
                    .to("BTC")
                    .amount(new BigDecimal("1.1"))
                    .build());
        });
    }
}