package org.falcon.service;

import org.falcon.entity.currency.Currency;
import org.falcon.entity.currency.CurrencyHistory;
import org.falcon.model.SupportedCurrencies;
import org.falcon.model.api.ApiConnection;
import org.falcon.repository.CurrencyHistoryRepository;
import org.falcon.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;
    private CurrencyHistoryRepository currencyHistoryRepository;
    private ApiConnection apiConnection;
    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, CurrencyHistoryRepository currencyHistoryRepository, ApiConnection apiConnection) {
        this.currencyRepository = currencyRepository;
        this.currencyHistoryRepository = currencyHistoryRepository;
        this.apiConnection = apiConnection;
    }
    @Async
    @Scheduled(fixedRate = 5000)
    public synchronized void updateCurrencies() {
        this.apiConnection.update();
        SupportedCurrencies.SUPPORTED_CURRENCIES.forEach(this::saveCurrency);
    }
    public void saveCurrency(Currency currency) {
        this.currencyRepository.save(currency);
        CurrencyHistory currencyHistory = new CurrencyHistory(currency);
        this.currencyHistoryRepository.save(currencyHistory);
    }
}
