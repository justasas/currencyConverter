package com.spectrocoin.currencyconverter.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyExchangeCommand {
    private BigDecimal amount;
    private String from;
    private String to;
}
