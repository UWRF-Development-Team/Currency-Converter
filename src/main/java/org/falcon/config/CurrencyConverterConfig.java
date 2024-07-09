package org.falcon.config;

import org.falcon.model.api.ApiConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConverterConfig {
    @Bean
    public ApiConnection apiConnection() {
        return new ApiConnection();
    }
}
