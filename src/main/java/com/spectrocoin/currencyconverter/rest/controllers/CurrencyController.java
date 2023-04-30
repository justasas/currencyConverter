package com.spectrocoin.currencyconverter.rest.controllers;

import com.spectrocoin.currencyconverter.domain.services.CurrencyExchangeService;
import com.spectrocoin.currencyconverter.domain.dto.CurrencyExchangeCommand;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("currencies")
public class CurrencyController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @RequestMapping("/convert")
    @GetMapping
    public BigDecimal convert(@RequestParam(value = "amount") BigDecimal amount,
                              @RequestParam(value = "from") @Size(min = 3, max = 3) String currencyFrom,
                              @RequestParam(value = "to") @Size(min = 3, max = 3) String currencyTo) {
        return currencyExchangeService.convert(CurrencyExchangeCommand.builder()
                .amount(amount)
                .from(currencyFrom)
                .to(currencyTo)
                .build());
    }

}
