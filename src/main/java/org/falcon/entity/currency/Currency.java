package org.falcon.entity.currency;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.falcon.model.api.ApiConnection;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private double value;
    private LocalDateTime lastUpdated;
    public Currency() {

    }
    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
        this.value = 0;
        this.lastUpdated = LocalDateTime.now();
        this.updateValueFromApi();
    }
    public Currency(String name, String code, double value) {
        this.name = name;
        this.code = code;
        this.value = value;
        this.lastUpdated = LocalDateTime.now();
    }
    public void updateValueFromApi() {
        ApiConnection apiConnection = new ApiConnection();
        apiConnection.fetchResponse();
        this.lastUpdated = LocalDateTime.now();
    }
}
