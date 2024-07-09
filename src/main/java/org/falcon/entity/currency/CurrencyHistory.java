package org.falcon.entity.currency;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "currency_histories")
public class CurrencyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private double value;
    private LocalDateTime timeUpdated;
    public CurrencyHistory() {
        this.name = "";
        this.code = "";
        this.value = 0.0;
        this.timeUpdated = LocalDateTime.now();
    }
    public CurrencyHistory(Currency currency) {
        this.name = currency.getName();
        this.code = currency.getCode();
        this.value = currency.getValue();
        this.timeUpdated = currency.getLastUpdated();
    }
}
