package junit;

import org.falcon.entity.currency.Currency;
import org.falcon.model.SupportedCurrencies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SupportedCurrenciesTest {

    @Test
    public void testCurrenciesNotNull() {
        boolean currenciesAreNull = false;
        for (Currency currency : SupportedCurrencies.SUPPORTED_CURRENCIES) {
            if (currency == null) {
                currenciesAreNull = true;
                break;
            }
        }
        assertFalse(currenciesAreNull);
    }
}
