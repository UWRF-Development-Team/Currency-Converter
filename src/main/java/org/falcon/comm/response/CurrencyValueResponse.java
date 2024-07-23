package org.falcon.comm.response;

import lombok.Data;

@Data
public class CurrencyValueResponse {
    private double value;
    public CurrencyValueResponse(double value) {
        this.value = value;
    }
}
