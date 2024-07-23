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
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "value")
    private double value;
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    public Currency() {
        this.name = "";
        this.code = "";
        this.value = 0.0;
        this.lastUpdated = LocalDateTime.now();
    }
    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
        this.value = 0;
        this.lastUpdated = LocalDateTime.now();
    }
    public Currency(String name, String code, double value) {
        this.name = name;
        this.code = code;
        this.value = value;
        this.lastUpdated = LocalDateTime.now();
    }
    public static void updateValueFromApi() {
        ApiConnection apiConnection = new ApiConnection();
        apiConnection.update();
    }
}
