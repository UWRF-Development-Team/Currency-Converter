package org.falcon.controller;

import org.falcon.comm.response.CurrencyValueResponse;
import org.falcon.model.SupportedCurrencies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @RequestMapping("/value/{code}")
    public ResponseEntity<CurrencyValueResponse> getCurrencyValue(@RequestParam("code") String code) {
        double currencyValue = SupportedCurrencies.getByCurrencyCode(code).getValue();
        return ResponseEntity.ok(new CurrencyValueResponse(currencyValue));
    }
}
