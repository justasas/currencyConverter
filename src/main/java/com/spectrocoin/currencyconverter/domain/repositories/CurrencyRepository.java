package com.spectrocoin.currencyconverter.domain.repositories;

import java.math.BigDecimal;

public interface CurrencyRepository {
    BigDecimal getExchangeRate(String currency);
}
