package com.spectrocoin.currencyconverter.domain.repositories;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CSVCurrencyRepository implements CurrencyRepository {

    private static final String CSV_FILE_NAME = "currencies.csv";
    private Map<String, BigDecimal> exchangeRateByCurrency;

    public CSVCurrencyRepository() {
        loadCurrencies();
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        return exchangeRateByCurrency.get(currency);
    }

    private void loadCurrencies() {
        try {
            exchangeRateByCurrency =
                    Files.lines(Path.of(ClassLoader.getSystemResource(CSV_FILE_NAME).toURI()))
                            .map(line -> line.split(","))
                            .collect(Collectors.toMap(e -> e[0], e -> new BigDecimal(e[1])));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
