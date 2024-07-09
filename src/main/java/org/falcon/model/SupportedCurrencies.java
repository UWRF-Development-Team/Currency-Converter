package org.falcon.model;

import org.falcon.entity.currency.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupportedCurrencies {
    public static final Currency USD = new Currency("United States Dollar", "USD");
    public static final Currency EUR = new Currency("Euro", "EUR");
    public static final Currency CHF = new Currency("Swiss Franc", "CHF");
    public static final Currency MXN = new Currency("Mexican Peso", "MXN");
    public static final Currency RUB = new Currency("Russian Ruble", "RUB");
    public static final Currency GBP = new Currency("British Pound", "GBP");
    public static final List<Currency> SUPPORTED_CURRENCIES;
    static {
        SUPPORTED_CURRENCIES = new ArrayList<>();
        SUPPORTED_CURRENCIES.add(USD);
        SUPPORTED_CURRENCIES.add(EUR);
        SUPPORTED_CURRENCIES.add(CHF);
        SUPPORTED_CURRENCIES.add(MXN);
        SUPPORTED_CURRENCIES.add(RUB);
        SUPPORTED_CURRENCIES.add(GBP);
    }
    public static void updateCurrenciesFromMap(Map<String, Double> currencyHashMap) {
        for (Map.Entry<String, Double> mapValues : currencyHashMap.entrySet()) {
            for (Currency supportedCurrency : SUPPORTED_CURRENCIES) {
                if (mapValues.getKey().equals(supportedCurrency.getCode())) {
                    supportedCurrency.setValue(mapValues.getValue());
                }
            }
        }
    }
}
