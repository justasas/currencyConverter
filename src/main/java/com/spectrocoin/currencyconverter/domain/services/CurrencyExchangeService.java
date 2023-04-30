package com.spectrocoin.currencyconverter.domain.services;

import com.spectrocoin.currencyconverter.domain.dto.CurrencyExchangeCommand;
import com.spectrocoin.currencyconverter.domain.exceptions.CurrencyConvertException;
import com.spectrocoin.currencyconverter.domain.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.HALF_UP;

@Service
public class CurrencyExchangeService {

    public static final Integer PRECISION = 18;
    private static final MathContext MATH_CONTEXT = new MathContext(PRECISION, HALF_UP);

    @Autowired
    private CurrencyRepository currencyRepository;

    public BigDecimal convert(CurrencyExchangeCommand command) {
        try {
            BigDecimal rateFrom = currencyRepository.getExchangeRate(command.getFrom());
            BigDecimal rateTo = currencyRepository.getExchangeRate(command.getTo());
            return rateTo.multiply(command.getAmount(), MATH_CONTEXT).divide(rateFrom, MATH_CONTEXT);
        } catch (Exception e) {
            throw new CurrencyConvertException("failed convert currencies", e);
        }
    }
}
