package org.falcon.model;

import org.falcon.entity.currency.Currency;

public class CurrencyConverter {
    public static Currency convertCurrency(Currency fromCurrency, Currency toCurrency, double amount) {
        double convertedAmount = convertCurrency(fromCurrency.getValue(), toCurrency.getValue(), amount);
        return new Currency(toCurrency.getName(), toCurrency.getCode(), convertedAmount);
    }
    public static double convertCurrency(double fromValue, double toValue, double amount) {
        if (toValue == 0.0) {
            throw new RuntimeException("Currency cannot be worth zero");
        }
        double ratePerUnit = toValue / fromValue;
        return amount * ratePerUnit;
    }
}
